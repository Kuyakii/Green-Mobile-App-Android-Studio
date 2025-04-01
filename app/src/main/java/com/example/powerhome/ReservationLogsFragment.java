package com.example.powerhome;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReservationLogsFragment extends Fragment {
    private final List<Reservation> reservationList = new ArrayList<>();
    private ReservationAdapter adapter;
    private int idResident = -1;

    public static ReservationLogsFragment newInstance(int idResident) {
        ReservationLogsFragment fragment = new ReservationLogsFragment();
        Bundle args = new Bundle();
        args.putInt("id_resident", idResident);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_logs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewReservation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReservationAdapter(reservationList);
        recyclerView.setAdapter(adapter);

        loadHistorique();

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idResident = getArguments().getInt("id_resident", -1);
        }
    }
    private void loadHistorique() {
        String url = SessionManager.HOST + "/www/getReservations.php?id_resident=" + idResident;

        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Reservation r = new Reservation();
                            r.nom_equipement = obj.getString("equipement_nom");
                            r.date = obj.getString("date");
                            r.heure = obj.getString("heure");
                            reservationList.add(r);
                        }

                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }
}