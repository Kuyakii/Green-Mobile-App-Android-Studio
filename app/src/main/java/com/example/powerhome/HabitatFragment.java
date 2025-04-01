package com.example.powerhome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HabitatFragment extends Fragment {

    private HabitatAdapter adapter;
    private final List<Habitat> habitatList = new ArrayList<>();
    private int idResident = -1;

    public static HabitatFragment newInstance(int idResident) {
        HabitatFragment fragment = new HabitatFragment();
        Bundle args = new Bundle();
        args.putInt("id_resident", idResident);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idResident = getArguments().getInt("id_resident", -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habitat, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHabitats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HabitatAdapter(habitatList);
        recyclerView.setAdapter(adapter);
        Button buttonActualiser = view.findViewById(R.id.buttonActualiser);
        buttonActualiser.setOnClickListener(v -> {
            habitatList.clear();
            loadHabitats(idResident);
            Toast.makeText(getContext(), getString(R.string.updated_habitats), Toast.LENGTH_SHORT).show();
        });

        Log.d("HABITAT_FRAGMENT", "onCreateView called");
        loadHabitats(idResident);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        habitatList.clear();
        loadHabitats(idResident);
    }

    private void loadHabitats(int idResident) {
        String url = SessionManager.HOST + "/www/getHabitats.php?id_resident=" + idResident;

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray dataArray = response.getJSONArray("data");
                        Log.d("API_RESPONSE", response.toString());

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject obj = dataArray.getJSONObject(i);

                            Habitat h = new Habitat();
                            h.id_habitat = obj.getInt("id_habitat");
                            h.habitat_nom = obj.getString("habitat_nom");
                            h.etage = obj.getInt("etage");

                            JSONArray equipArray = obj.getJSONArray("equipements");
                            for (int j = 0; j < equipArray.length(); j++) {
                                JSONObject eq = equipArray.getJSONObject(j);
                                Equipement e = new Equipement();
                                e.id_equipement = eq.getInt("id_equipement");
                                e.nom = eq.getString("nom");
                                e.consommation_watt = eq.getInt("consommation_watt");
                                e.etat = eq.getString("etat");

                                h.equipements.add(e);
                            }

                            habitatList.add(h);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}