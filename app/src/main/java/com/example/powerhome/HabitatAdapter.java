package com.example.powerhome;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class HabitatAdapter extends RecyclerView.Adapter<HabitatAdapter.HabitatViewHolder> {

    private final List<Habitat> habitatList;

    public HabitatAdapter(List<Habitat> habitatList) {
        this.habitatList = habitatList;
    }

    public static class HabitatViewHolder extends RecyclerView.ViewHolder {
        TextView textHabitatNom, textEquipements, textEtage;

        public HabitatViewHolder(View itemView) {
            super(itemView);
            textHabitatNom = itemView.findViewById(R.id.textHabitatNom);
            textEquipements = itemView.findViewById(R.id.textEquipements);
            textEtage = itemView.findViewById(R.id.textEtage);
        }
    }

    @NonNull
    @Override
    public HabitatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habitat, parent, false);
        return new HabitatViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HabitatViewHolder holder, int position) {
        Habitat habitat = habitatList.get(position);

        holder.textHabitatNom.setText(
                holder.itemView.getContext().getString(R.string.habitat) + " " + habitat.habitat_nom
        );
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable icHome = holder.textHabitatNom.getContext().getDrawable(R.drawable.ic_home);
        if (icHome != null) {
            int size = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 24, holder.itemView.getResources().getDisplayMetrics()
            );
            icHome.setBounds(0, 0, size, size);
            holder.textHabitatNom.setCompoundDrawables(icHome, null, null, null);
            holder.textHabitatNom.setCompoundDrawablePadding(20);
        }

        StringBuilder equipementsStr = new StringBuilder();
        for (Equipement eq : habitat.equipements) {
            equipementsStr
                    .append(eq.nom)
                    .append(" (")
                    .append(eq.consommation_watt)
                    .append("W, ")
                    .append(eq.etat)
                    .append(")\n");
        }

        holder.textEquipements.setText(equipementsStr.toString().trim());
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable icTool = holder.textEquipements.getContext().getDrawable(R.drawable.ic_tool);
        if (icTool != null) {
            int size = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 20, holder.itemView.getResources().getDisplayMetrics()
            );
            icTool.setBounds(0, 0, size, size);
            holder.textEquipements.setCompoundDrawables(icTool, null, null, null);
            holder.textEquipements.setCompoundDrawablePadding(20);
        }

        holder.textEtage.setText(holder.textEtage.getContext().getString(R.string.habitat_stage) + " " + habitat.etage);
    }

    @Override
    public int getItemCount() {
        return habitatList.size();
    }
}
