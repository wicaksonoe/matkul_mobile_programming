package com.wicak.projectuas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class FilmDaftarActivity extends AppCompatActivity {
    ArrayList<Film> daftar = new ArrayList<>();
    RecyclerView filmRecyclerView;

    SQLite sqLite = new SQLite(FilmDaftarActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_film);
        getSupportActionBar().setTitle("Daftar Film");

        filmRecyclerView = findViewById(R.id.recycler_view_film);
        filmRecyclerView.setHasFixedSize(true);

        showRecyclerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_film, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int selected_menu = item.getItemId();

        switch (selected_menu) {
            case R.id.action_film_tambah:
                Intent moveToFilmKelola = new Intent(FilmDaftarActivity.this, FilmTambahActivity.class);
                startActivityForResult(moveToFilmKelola, 1);
                break;

            case R.id.action_film_refresh:
                showRecyclerList();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            showRecyclerList();
        }
    }

    public void showRecyclerList() {
        // add all data to collection
        daftar.clear();
        daftar.addAll(LoadDaftarFilm());

        // set layout manager
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set adapter to use daftar collection
        FilmListAdapter filmListAdapter = new FilmListAdapter(daftar);

        // set recycler view to use filmListAdapter as adapter
        filmRecyclerView.setAdapter(filmListAdapter);

        // onClickCallback on every list item
        filmListAdapter.setOnItemClickCallback(new FilmListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(final Film data) {
                final CharSequence[] dialogItems = {"Edit", "Delete"};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FilmDaftarActivity.this);

                alertDialog.setTitle("Kelola Film");
                alertDialog.setItems(dialogItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                Intent moveToFilmEdit = new Intent(FilmDaftarActivity.this, FilmEditActivity.class);
                                moveToFilmEdit.putExtra(FilmEditActivity.ID_FILM, data.getId());
                                moveToFilmEdit.putExtra(FilmEditActivity.JUDUL_FILM, data.getJudulFilm());
                                moveToFilmEdit.putExtra(FilmEditActivity.HARGA_TIKET, data.getHarga());
                                moveToFilmEdit.putExtra(FilmEditActivity.WAKTU_TAYANG, data.getWaktuTayang());
                                startActivityForResult(moveToFilmEdit, 1);
                                break;

                            case 1:
                                SQLiteDatabase db = sqLite.getWritableDatabase();
                                SQLiteStatement delete_tiket = db.compileStatement("DELETE FROM tiket WHERE id_film=?");
                                delete_tiket.bindLong(1, data.getId());
                                delete_tiket.executeUpdateDelete();

                                SQLiteStatement delete_film = db.compileStatement("DELETE FROM film WHERE id=?");
                                delete_film.bindLong(1, data.getId());
                                delete_film.executeUpdateDelete();
                                Toast.makeText(FilmDaftarActivity.this, "Data berhasil direfresh", Toast.LENGTH_SHORT).show();
                                showRecyclerList();
                                break;
                        }
                    }
                });
                alertDialog.create().show();
            }
        });

        Toast.makeText(FilmDaftarActivity.this, "Data berhasil direfresh", Toast.LENGTH_SHORT).show();
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
