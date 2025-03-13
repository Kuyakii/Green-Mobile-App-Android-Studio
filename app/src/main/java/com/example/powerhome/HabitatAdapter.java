// HabitatAdapter.java
package com.example.powerhome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class HabitatAdapter extends BaseAdapter {
    private final Context context;
    private final List<Habitat> habitats;
    private final LayoutInflater inflater;

    public HabitatAdapter(Context context, List<Habitat> habitats) {
        this.context = context;
        this.habitats = habitats;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return habitats.size();
    }

    @Override
    public Object getItem(int position) {
        return habitats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_habitat, parent, false);
        }

        TextView nomTextView = convertView.findViewById(R.id.nomResident);
        TextView equipementsTextView = convertView.findViewById(R.id.nbEquipements);
        TextView etageTextView = convertView.findViewById(R.id.etage);
        LinearLayout equipementsLayout = convertView.findViewById(R.id.equipementsLayout);

        Habitat habitat = habitats.get(position);
        nomTextView.setText(habitat.getName());

        Context context = parent.getContext();
        equipementsTextView.setText(habitat.getAppliances().size() +
                (habitat.getAppliances().size() > 1 ? " " + context.getString(R.string.appliances) : " " + context.getString(R.string.appliance)));

        etageTextView.setText("" + habitat.getFloor());

        equipementsLayout.removeAllViews();
        for (Appliance appliance : habitat.getAppliances()) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(appliance.getIcon());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, 50);
            params.setMargins(0, 0, 10, 0);

            imageView.setLayoutParams(params);
            equipementsLayout.addView(imageView);
        }

        return convertView;
    }
}
