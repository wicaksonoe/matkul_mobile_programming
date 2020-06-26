package com.wicak.projectuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ListViewHolder> {
    private ArrayList<Film> daftarFilm;
    private OnItemClickCallback onItemClickCallback;

    public FilmListAdapter(ArrayList<Film> daftarFilm) {
        this.daftarFilm = daftarFilm;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_film, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Film film = daftarFilm.get(position);
        holder.tvJudulFilm.setText(film.getJudulFilm());
        holder.tvDescription.setText("Waktu tayang: " + film.getWaktuTayang() + " | Harga tiket: " + film.getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(daftarFilm.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarFilm.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudulFilm, tvDescription;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudulFilm = itemView.findViewById(R.id.judul_film);
            tvDescription = itemView.findViewById(R.id.deskripsi);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Film data);
    }
}
