
package com.example.powerhome;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeekGridAdapter extends RecyclerView.Adapter<WeekGridAdapter.ViewHolder> {

    private final List<WeekCell> weekCells;

    public WeekGridAdapter(List<WeekCell> weekCells) {
        this.weekCells = weekCells;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView heure, conso;
        View container;

        public ViewHolder(View view) {
            super(view);
            heure = view.findViewById(R.id.textHeureCreneau);
            conso = view.findViewById(R.id.textConsoCreneau);
            container = view;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_creneau, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeekCell cell = weekCells.get(position);
        holder.heure.setText(cell.heureDebut);
        holder.conso.setText(cell.consommation + " W");

        if (cell.consommation == 0) {
            holder.container.setBackgroundColor(Color.parseColor("#444444")); // fond gris neutre
        } else if (cell.pourcentageJour < 0.3f) {
            holder.container.setBackgroundColor(Color.parseColor("#88CC88")); // vert
        } else if (cell.pourcentageJour < 0.7f) {
            holder.container.setBackgroundColor(Color.parseColor("#FFD580")); // orange
        } else {
            holder.container.setBackgroundColor(Color.parseColor("#FF8C8C")); // rouge
        }    }

    @Override
    public int getItemCount() {
        return weekCells.size();
    }
}
