
package com.example.powerhome;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarFragment extends Fragment {

    RecyclerView recyclerView;
    Spinner spinnerYear, spinnerMonth;
    ImageButton prevWeekBtn, nextWeekBtn;
    Date minDate, maxDate = null;
    int idResident;

    Map<String, List<Consommation>> consoByDate = new HashMap<>();
    List<String> joursSemaine = new ArrayList<>();
    int selectedYear, selectedMonth;
    Calendar currentWeekStart = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        spinnerYear = view.findViewById(R.id.spinnerYear);
        spinnerMonth = view.findViewById(R.id.spinnerMonth);
        recyclerView = view.findViewById(R.id.recyclerViewCalendrier);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        prevWeekBtn = view.findViewById(R.id.buttonPrevWeek);
        nextWeekBtn = view.findViewById(R.id.buttonNextWeek);

        prevWeekBtn.setOnClickListener(v -> {
            Calendar test = (Calendar) currentWeekStart.clone();
            test.add(Calendar.DAY_OF_MONTH, -3);

            Calendar minLimit = Calendar.getInstance();
            minLimit.setTime(minDate);
            minLimit.set(Calendar.HOUR_OF_DAY, 0);
            minLimit.set(Calendar.MINUTE, 0);
            minLimit.set(Calendar.SECOND, 0);
            minLimit.set(Calendar.MILLISECOND, 0);

            if (!test.before(minLimit)) {
                currentWeekStart = test;
                updateSpinnersFromCalendar();
                refreshGrid();
            }
        });


        nextWeekBtn.setOnClickListener(v -> {
            Calendar test = (Calendar) currentWeekStart.clone();
            test.add(Calendar.DAY_OF_MONTH, 3);
            if (maxDate == null || !test.getTime().after(maxDate)) {
                currentWeekStart = test;
                updateSpinnersFromCalendar();
                refreshGrid();
            }
        });

        SessionManager session = new SessionManager(requireContext());
        idResident = session.getId();
        loadHabitatId(idResident);

        return view;
    }

    private void loadHabitatId(int idResident) {
        String url = SessionManager.HOST + "/www/getHabitats.php?id_resident=" + idResident;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray dataArray = response.getJSONArray("data");
                        if (dataArray.length() > 0) {
                            int idHabitat = dataArray.getJSONObject(0).getInt("id_habitat");
                            loadConso(idHabitat);
                        } else {
                            Toast.makeText(getContext(), "Aucun habitat trouvé.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }
    @Override
    public void onResume() {
        super.onResume();
        consoByDate.clear();
        loadConso(idResident);
    }
    private void loadConso(int idHabitat) {
        String url = SessionManager.HOST + "/www/getConsommation.php?id_habitat=" + idHabitat;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Consommation c = new Consommation();
                            c.date = obj.getString("date"); // YYYY-MM-DD
                            c.heure = obj.getString("heure").substring(0, 5);
                            c.consommation_watt = obj.getInt("consommation_watt");

                            consoByDate.computeIfAbsent(c.date, k -> new ArrayList<>()).add(c);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            Date dateParsed = sdf.parse(c.date);

                            if (minDate == null || dateParsed.before(minDate)) minDate = dateParsed;
                        }


                        setupSpinners();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Toast.makeText(getContext(), "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(requireContext()).add(request);
    }

    private void setupSpinners() {
        Set<Integer> years = new HashSet<>();
        Set<Integer> months = new HashSet<>();

        for (String dateStr : consoByDate.keySet()) {
            String[] parts = dateStr.split("-");
            years.add(Integer.parseInt(parts[0]));
            months.add(Integer.parseInt(parts[1]));
        }

        List<Integer> sortedYears = new ArrayList<>(years);
        List<Integer> sortedMonths = new ArrayList<>(months);
        Collections.sort(sortedYears);
        Collections.sort(sortedMonths);

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(requireContext(), R.layout.custom_spinner_item, sortedYears);
        yearAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerYear.setAdapter(yearAdapter);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(requireContext(), R.layout.custom_spinner_item);
        monthAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);

        String[] allMonths = new java.text.DateFormatSymbols(Locale.getDefault()).getMonths();
        for (int i = 0; i < 12; i++) {
            monthAdapter.add(allMonths[i]);
        }
        spinnerMonth.setAdapter(monthAdapter);

        spinnerYear.setSelection(0);
        spinnerMonth.setSelection(0);

        selectedYear = sortedYears.get(0);
        Calendar maxCal = Calendar.getInstance();
        maxCal.set(Calendar.YEAR, selectedYear);
        maxCal.set(Calendar.MONTH, Calendar.DECEMBER);
        maxCal.set(Calendar.DAY_OF_MONTH, 31);
        maxCal.set(Calendar.HOUR_OF_DAY, 23);
        maxCal.set(Calendar.MINUTE, 59);
        maxCal.set(Calendar.SECOND, 59);

        maxDate = maxCal.getTime();

        selectedMonth = 1;

        spinnerYear.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = position + 1;
                refreshGrid();
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        spinnerMonth.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = position + 1;

                currentWeekStart.set(Calendar.YEAR, selectedYear);
                currentWeekStart.set(Calendar.MONTH, selectedMonth - 1);
                currentWeekStart.set(Calendar.DAY_OF_MONTH, 1);

                refreshGrid();
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // Initialiser semaine courante au premier jour du mois sélectionné
        currentWeekStart.set(Calendar.YEAR, selectedYear);
        currentWeekStart.set(Calendar.MONTH, selectedMonth - 1);
        currentWeekStart.set(Calendar.DAY_OF_MONTH, 1);
        while (currentWeekStart.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            currentWeekStart.add(Calendar.DAY_OF_MONTH, -1);
        }

        refreshGrid();
    }

    private void refreshGrid() {
        List<WeekCell> cells = new ArrayList<>();
        List<String> creneaux = CreneauUtils.getCreneaux();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        LinearLayout headerLayout = requireView().findViewById(R.id.headerDaysLayout);
        headerLayout.removeAllViews();
        SimpleDateFormat labelFormat = new SimpleDateFormat("dd EEE", Locale.getDefault());

        for (int i = 0; i < 3; i++) {
            Calendar jourCal = (Calendar) currentWeekStart.clone();
            jourCal.add(Calendar.DAY_OF_MONTH, i);
            String label = labelFormat.format(jourCal.getTime());

            TextView dayTitle = new TextView(requireContext());
            dayTitle.setText(label);
            dayTitle.setTextColor(getResources().getColor(android.R.color.white));
            dayTitle.setTextSize(14f);
            dayTitle.setBackgroundColor(Color.parseColor("#66000000"));
            dayTitle.setPadding(8, 0, 8, 8);
            dayTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            dayTitle.setLayoutParams(params);
            headerLayout.addView(dayTitle);
        }

        for (String hDebut : creneaux) {
            for (int i = 0; i < 3; i++) {
                Calendar jourCal = (Calendar) currentWeekStart.clone();
                jourCal.add(Calendar.DAY_OF_MONTH, i);
                String dateStr = sdf.format(jourCal.getTime());
                List<Consommation> consoJour = consoByDate.getOrDefault(dateStr, new ArrayList<>());

                int totalJour = 0;
                for (Consommation c : consoJour) totalJour += c.consommation_watt;

                int consoCreneau = 0;
                for (Consommation c : consoJour) {
                    if (CreneauUtils.getCreneauForHour(c.heure).equals(hDebut)) {
                        consoCreneau += c.consommation_watt;
                    }
                }

                float p = (totalJour == 0) ? 0f : (float) consoCreneau / totalJour;
                String jourNom = new SimpleDateFormat("EEEE", Locale.FRENCH).format(jourCal.getTime());

                cells.add(new WeekCell(jourNom, hDebut, consoCreneau, p));
            }
        }

        recyclerView.setAdapter(new WeekGridAdapter(cells));
    }

    private void updateSpinnersFromCalendar() {
        int year = currentWeekStart.get(Calendar.YEAR);
        int month = currentWeekStart.get(Calendar.MONTH) + 1;

        boolean yearFound = false;
        for (int i = 0; i < spinnerYear.getCount(); i++) {
            if ((int) spinnerYear.getItemAtPosition(i) == year) {
                spinnerYear.setSelection(i, false);
                selectedYear = year;
                yearFound = true;
                break;
            }
        }

        boolean monthFound = false;
        for (int i = 0; i < spinnerMonth.getCount(); i++) {
            String moisNom = new java.text.DateFormatSymbols(Locale.getDefault()).getMonths()[month - 1];
            if (spinnerMonth.getItemAtPosition(i).toString().equalsIgnoreCase(moisNom)) {
                spinnerMonth.setSelection(i, false);
                selectedMonth = month;
                monthFound = true;
                break;
            }
        }
        if (!yearFound || !monthFound) return;
    }
}