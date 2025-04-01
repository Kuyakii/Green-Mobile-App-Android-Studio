package com.example.powerhome;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private final List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView textEquipement, textDate, textHeure;

        public ReservationViewHolder(View itemView) {
            super(itemView);
            textEquipement = itemView.findViewById(R.id.textEquipement);
            textDate = itemView.findViewById(R.id.textDate);
            textHeure = itemView.findViewById(R.id.textHeure);
        }
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation r = reservationList.get(position);

        holder.textEquipement.setText(holder.itemView.getContext().getString(R.string.equipements) + " : " + r.nom_equipement);
        holder.textDate.setText(holder.itemView.getContext().getString(R.string.date) + " : " + r.date);
        holder.textHeure.setText(holder.itemView.getContext().getString(R.string.hour) + " : " + r.heure);

    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }
}
