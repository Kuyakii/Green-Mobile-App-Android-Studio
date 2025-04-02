package com.example.powerhome;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {
    private TextView textCoins, textMessage;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        textCoins = view.findViewById(R.id.textCoins);
        textMessage = view.findViewById(R.id.textMessage);
        progressBar = view.findViewById(R.id.progressBarEco);

        loadEcoCoins();

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void loadEcoCoins() {
        SessionManager session = new SessionManager(requireContext());
        int id = session.getId();
        String url = "http://192.168.1.134/www/getResident.php?id=" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject resident = response.getJSONObject("resident");
                        int coins = resident.getInt("eco_coin");

                        textCoins.setText(coins + " " + getString(R.string.eco_coins));
                        progressBar.setProgress(coins % 100);

                        if (coins >= 200) {
                            textMessage.setText(R.string.title_eco_master);
                        } else if (coins >= 100) {
                            textMessage.setText(R.string.title_green_protector);
                        } else {
                            textMessage.setText(R.string.title_eco_apprentice);
                        }

                        session.setEcoCoins(coins);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Erreur de données", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getContext(), "Erreur réseau : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }

}
