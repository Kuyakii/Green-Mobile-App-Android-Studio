package com.example.powerhome;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReservationFragment extends Fragment {

    private Spinner spinnerHabitat, spinnerEquipement;
    private EditText editDate, editHeureDebut, editHeureFin;

    private final List<Habitat> habitats = new ArrayList<>();
    private final Map<Integer, List<Equipement>> equipementsParHabitat = new HashMap<>();

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        spinnerHabitat = view.findViewById(R.id.spinnerHabitat);
        spinnerEquipement = view.findViewById(R.id.spinnerEquipement);
        editDate = view.findViewById(R.id.editDate);
        editHeureDebut = view.findViewById(R.id.editHeureDebut);
        editHeureFin = view.findViewById(R.id.editHeureFin);
        Button buttonReserver = view.findViewById(R.id.buttonReserver);

        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(requireContext(), (view1, year, month, dayOfMonth) ->
                    editDate.setText(String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)),
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        View.OnClickListener timeClickListener = v -> {
            EditText target = (EditText) v;
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(getContext(), (view1, hour, minute) ->
                    target.setText(String.format("%02d:%02d:00", hour, minute)),
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        };

        editHeureDebut.setOnClickListener(timeClickListener);
        editHeureFin.setOnClickListener(timeClickListener);

        buttonReserver.setOnClickListener(v -> {
            Habitat selectedHabitat = (Habitat) spinnerHabitat.getSelectedItem();
            Equipement selectedEquip = (Equipement) spinnerEquipement.getSelectedItem();
            String date = editDate.getText().toString();
            String debut = editHeureDebut.getText().toString();
            String fin = editHeureFin.getText().toString();

            if (selectedHabitat != null && selectedEquip != null && !date.isEmpty() && !debut.isEmpty() && !fin.isEmpty()) {
                reserver(selectedHabitat.id_habitat, selectedEquip.id_equipement, date, debut, fin);
            } else {
                Toast.makeText(getContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            }

        });

        loadHabitats();

        return view;
    }

    private void loadHabitats() {
        SessionManager session = new SessionManager(requireContext());

        int idResident = session.getId();

        String url = "http://192.168.1.134/www/getHabitats.php?id_resident=" + idResident;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray dataArray = response.getJSONArray("data");
                        habitats.clear();
                        equipementsParHabitat.clear();

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject obj = dataArray.getJSONObject(i);

                            Habitat h = new Habitat();
                            h.id_habitat = obj.getInt("id_habitat");
                            h.habitat_nom = obj.getString("habitat_nom");

                            JSONArray equipArray = obj.getJSONArray("equipements");
                            List<Equipement> equipList = new ArrayList<>();

                            for (int j = 0; j < equipArray.length(); j++) {
                                JSONObject eq = equipArray.getJSONObject(j);
                                Equipement e = new Equipement(
                                        eq.getInt("id_equipement"),
                                        eq.getString("nom")
                                );
                                equipList.add(e);
                            }

                            habitats.add(h);
                            equipementsParHabitat.put(h.id_habitat, equipList);
                        }

                        ArrayAdapter<Habitat> habitatAdapter = new ArrayAdapter<>(
                                requireContext(),
                                R.layout.custom_spinner_item,
                                habitats
                        );
                        habitatAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                        spinnerHabitat.setAdapter(habitatAdapter);

                        spinnerHabitat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Habitat selected = habitats.get(position);
                                List<Equipement> list = equipementsParHabitat.get(selected.id_habitat);
                                if(list != null) {
                                    ArrayAdapter<Equipement> adapter = new ArrayAdapter<>(
                                            requireContext(),
                                            R.layout.custom_spinner_item,
                                            list
                                    );
                                    adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                                    spinnerEquipement.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {}
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }

    private void reserver(int idHabitat, int idEquipement, String date, String debut, String fin) {
        SessionManager session = new SessionManager(requireContext());
        int idResident = session.getId();

        String url = SessionManager.HOST + "/www/reserverCreneau.php";

        JSONObject data = new JSONObject();
        try {
            data.put("id_resident", idResident);
            data.put("id_habitat", idHabitat);
            data.put("id_equipement", idEquipement);
            data.put("date", date);
            data.put("heure_debut", debut);
            data.put("heure_fin", fin);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, data,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            String message = response.getString("message");
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

                            // ✅ Nouveau solde eco-coin
                            if (response.has("eco_coin")) {
                                int coins = response.getInt("eco_coin");
                                session.setEcoCoins(coins);
                            }

                            // Reset champs
                            editDate.setText("");
                            editHeureDebut.setText("");
                            editHeureFin.setText("");
                            spinnerEquipement.setSelection(0);

                        } else {
                            Toast.makeText(getContext(), getString(R.string.error) + response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), getString(R.string.error) + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }

    public static class Equipement {
        int id_equipement;
        String nom;

        public Equipement(int id, String nom) {
            this.id_equipement = id;
            this.nom = nom;
        }

        @NonNull
        @Override
        public String toString() {
            return nom;
        }
    }

    public static class Habitat {
        int id_habitat;
        String habitat_nom;

        @NonNull
        @Override
        public String toString() {
            return habitat_nom;
        }
    }
}
