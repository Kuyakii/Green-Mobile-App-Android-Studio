package com.example.powerhome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
public class CalendarFragment extends Fragment {

    RecyclerView recyclerView;
    ConsommationAdapter adapter;
    List<Consommation> consoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCalendrier);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new ConsommationAdapter(consoList);
        recyclerView.setAdapter(adapter);

        SessionManager session = new SessionManager(requireContext());
        int idResident = session.getId();

        loadHabitatId(idResident);
        return view;
    }

    private void loadConso(int idHabitat) {
        String url = SessionManager.HOST + "/www/getConsommation.php?id_habitat=" + idHabitat;

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray data = response.getJSONArray("data");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Consommation c = new Consommation();
                            c.date = obj.getString("date");
                            c.heure = obj.getString("heure");
                            c.consommation_watt = obj.getInt("consommation_watt");
                            consoList.add(c);
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
    private void loadHabitatId(int idResident) {
        String url = SessionManager.HOST + "/www/getHabitats.php?id_resident=" + idResident;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray dataArray = response.getJSONArray("data");
                        if (dataArray.length() > 0) {
                            JSONObject firstHabitat = dataArray.getJSONObject(0);
                            int idHabitat = firstHabitat.getInt("id_habitat");
                            loadConso(idHabitat);
                        } else {
                            Toast.makeText(getContext(), getString(R.string.no_habitats_for_resident), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }

}
