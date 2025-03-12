package com.example.powerhome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HabitatActivity extends Activity {
    private List<Habitat> habitats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat);

        ListView listView = findViewById(R.id.listViewHabitats);
        habitats = new ArrayList<>();

        int ic_cleaner = R.drawable.ic_cleaner;
        int ic_clim = R.drawable.ic_clim;
        int ic_laundry = R.drawable.ic_laundry;
        int ic_iron = R.drawable.ic_iron;

        Appliance cleaner = new Appliance(1, "cleaner", null, null, ic_cleaner);
        Appliance clim = new Appliance(2, "clim", null, null, ic_clim);
        Appliance laundry = new Appliance(3, "laundry", null, null, ic_laundry);
        Appliance iron = new Appliance(4, "iron", null, null, ic_iron);

        // Ajouter des habitants avec équipements
        habitats.add(new Habitat(1, "Gaëtan Leclair", 1, Arrays.asList(laundry, cleaner, clim, iron)));
        habitats.add(new Habitat(2, "Cédric Boudet", 1, Arrays.asList(laundry)));
        habitats.add(new Habitat(3, "Gaylord Thibodeaux", 2, Arrays.asList(iron, cleaner)));
        habitats.add(new Habitat(4, "Adam Jacquinot", 3, Arrays.asList(laundry, cleaner, iron)));
        habitats.add(new Habitat(5, "Abel Fresnel", 3, Arrays.asList(cleaner)));

        HabitatAdapter adapter = new HabitatAdapter(this, habitats);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habitat habitat = habitats.get(position);
                Toast.makeText(HabitatActivity.this, R.string.resident + " " + habitat.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
