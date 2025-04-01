package com.example.powerhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        TextView textNom = view.findViewById(R.id.textNom);
        TextView textPrenom = view.findViewById(R.id.textPrenom);
        TextView textEmail = view.findViewById(R.id.textEmail);

        SessionManager session = new SessionManager(requireContext());

        textNom.setText(session.getNom());
        textPrenom.setText(session.getPrenom());
        textEmail.setText(session.getEmail());

        return view;
    }
}
