package com.wicak.projectuas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class KursiMainActivity extends AppCompatActivity {
    ArrayList<Film> daftar = new ArrayList<>();
    RecyclerView filmRecyclerView;

    SQLite sqLite = new SQLite(KursiMainActivity.this);

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_kursi_main);
        getSupportActionBar().setTitle("Kelola Kursi");

        filmRecyclerView = findViewById(R.id.recycler_view_film);
        filmRecyclerView.setHasFixedSize(true);

        showRecyclerList();
    }

    private void showRecyclerList() {
        daftar.clear();
        daftar.addAll(LoadDaftarFilm());

        // set layout manager
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set adapter to use daftar collection
        FilmListAdapter filmListAdapter = new FilmListAdapter(daftar);

        // set recycler view to use filmListAdapter as adapter
        filmRecyclerView.setAdapter(filmListAdapter);

        filmListAdapter.setOnItemClickCallback(new FilmListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(final Film data) {
                Intent moveToKursiPesan = new Intent(getApplicationContext(), KursiPesanActivity.class);
                moveToKursiPesan.putExtra(KursiPesanActivity.ID_FILM, data.getId());
                moveToKursiPesan.putExtra(KursiPesanActivity.JUDUL_FILM, data.getJudulFilm());
                startActivity(moveToKursiPesan);
            }
        });

        Toast.makeText(getApplicationContext(), "Data berhasil direfresh", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Film> LoadDaftarFilm() {
        ArrayList<Film> results = new ArrayList<>();
        SQLiteDatabase db = sqLite.getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM film", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            Film row = new Film();
            row.setId(cursor.getInt(0));
            row.setJudulFilm(cursor.getString(1));
            row.setWaktuTayang(cursor.getString(2));
            row.setHarga(cursor.getFloat(3));
            row.setCreatedBy(cursor.getString(4));

            results.add(row);
        }

        return results;
    }
}
