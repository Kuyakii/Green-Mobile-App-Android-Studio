package com.example.powerhome;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConsommationAdapter extends RecyclerView.Adapter<ConsommationAdapter.ConsoViewHolder> {

    private final List<Consommation> consommationList;

    public ConsommationAdapter(List<Consommation> list) {
        this.consommationList = list;
    }

    public static class ConsoViewHolder extends RecyclerView.ViewHolder {
        TextView textHeure, textConso;
        View root;

        public ConsoViewHolder(View itemView) {
            super(itemView);
            textHeure = itemView.findViewById(R.id.textHeure);
            textConso = itemView.findViewById(R.id.textConso);
            root = itemView;
        }
    }

    @NonNull
    @Override
    public ConsoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_consommation, parent, false);
        return new ConsoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ConsoViewHolder holder, int position) {
        Consommation c = consommationList.get(position);
        holder.textHeure.setText(c.heure);
        holder.textConso.setText(c.consommation_watt + " W");
        Drawable ic_sun = ContextCompat.getDrawable(holder.itemView.getContext(), getIconForHour(c.heure));
        if (ic_sun != null) {

            int size = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 30, holder.itemView.getResources().getDisplayMetrics()
            );
            ic_sun.setBounds(0, 0, size, size);
            holder.textHeure.setCompoundDrawables(ic_sun, null, null, null);
            holder.textHeure.setCompoundDrawablePadding(20);
        }

        int watt = c.consommation_watt;
        if (watt < 200) {
            holder.root.setBackgroundResource(R.drawable.conso_background_low);
        } else if (watt <= 600) {
            holder.root.setBackgroundResource(R.drawable.conso_background_medium);
        } else {
            holder.root.setBackgroundResource(R.drawable.conso_background_high);
        }
    }

    @Override
    public int getItemCount() {
        return consommationList.size();
    }

    private int getIconForHour(String heure) {
        int hour = Integer.parseInt(heure.substring(0, 2));
        if (hour >= 6 && hour < 9) return R.drawable.ic_sunrise;
        if (hour >= 9 && hour < 18) return R.drawable.ic_sun;
        if (hour >= 18 && hour < 21) return R.drawable.ic_sunset;
        return R.drawable.ic_moon;
    }
}