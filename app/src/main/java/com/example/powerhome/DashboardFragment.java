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
        int localCoins = session.getEcoCoins();
        textCoins.setText(localCoins +" "+ getString(R.string.eco_coins));
        progressBar.setProgress(localCoins % 100);
        if (localCoins >= 200) {
            textMessage.setText(R.string.title_eco_master);
        } else if (localCoins >= 100) {
            textMessage.setText(R.string.title_green_protector);
        } else {
            textMessage.setText(R.string.title_eco_apprentice);
        }
    }
}
