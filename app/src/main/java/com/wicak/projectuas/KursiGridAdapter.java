package com.wicak.projectuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KursiGridAdapter extends RecyclerView.Adapter<KursiGridAdapter.GridViewHolder> {
    private ArrayList<Kursi> daftarKursi;
    private OnItemClickCallback onItemClickCallback;

    public KursiGridAdapter(ArrayList<Kursi> daftarKursi) {
        this.daftarKursi = daftarKursi;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_kursi, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Kursi kursi = daftarKursi.get(position);

        holder.btnKursi.setText(kursi.getNomorSeat());

        if (kursi.getStatus() == 0) {
            holder.textKursiStatus.setText("Available");
        } else if (kursi.getStatus() == 1) {
            holder.textKursiStatus.setText("Booked");
        }

        holder.btnKursi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(daftarKursi.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarKursi.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        Button btnKursi;
        TextView textKursiStatus;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);

            btnKursi = itemView.findViewById(R.id.button_kursi);
            textKursiStatus = itemView.findViewById(R.id.text_kursi_status);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Kursi data);
    }
}
