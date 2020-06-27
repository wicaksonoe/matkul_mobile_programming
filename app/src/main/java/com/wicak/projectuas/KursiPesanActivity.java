package com.wicak.projectuas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class KursiPesanActivity extends AppCompatActivity {
    public static final String ID_FILM = "id_film";
    public static final String JUDUL_FILM = "judul_film";

    private int idFilm;
    private String judulFilm;

    ArrayList<Kursi> daftarKursi = new ArrayList<>();
    RecyclerView kursiRecyclerView;

    SQLite sqLite = new SQLite(KursiPesanActivity.this);

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_kursi_pesan);

        idFilm = getIntent().getIntExtra(ID_FILM, 0);
        judulFilm = getIntent().getStringExtra(JUDUL_FILM);

        getSupportActionBar().setTitle("Kelola Kursi Film " + judulFilm);

        kursiRecyclerView = findViewById(R.id.recycler_view_kursi);
        kursiRecyclerView.setHasFixedSize(true);

        showRecyclerGrid();
    }

    private void showRecyclerGrid() {
        daftarKursi.clear();
        daftarKursi.addAll(loadDaftarKursi());

        kursiRecyclerView.setLayoutManager(new GridLayoutManager(this, 8));
        KursiGridAdapter kursiGridAdapter = new KursiGridAdapter(daftarKursi);
        kursiRecyclerView.setAdapter(kursiGridAdapter);

        kursiGridAdapter.setOnItemClickCallback(new KursiGridAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(final Kursi data) {
                CharSequence[] dialogItems = new CharSequence[1];
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(KursiPesanActivity.this);
                alertDialog.setTitle("Kelola Tiket");

                if (data.getStatus() == 0) {
                    dialogItems[0] = "Pesan Kursi";
                    alertDialog.setItems(dialogItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    pesanKursi(data);
                                    Toast.makeText(KursiPesanActivity.this, "Kursi " + data.getNomorSeat() + " Telah Dipesan", Toast.LENGTH_LONG).show();
                                    showRecyclerGrid();
                                    break;
                            }
                        }
                    });
                } else {
                    dialogItems[0] = "Kosongkan Kursi";
                    alertDialog.setItems(dialogItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    kosongkanKursi(data);
                                    Toast.makeText(KursiPesanActivity.this, "Kursi " + data.getNomorSeat() + " Telah Dikosongkan", Toast.LENGTH_LONG).show();
                                    showRecyclerGrid();
                                    break;
                            }
                        }
                    });
                }

                alertDialog.create().show();
            }
        });
    }

    private void kosongkanKursi(Kursi data) {
        // do SQL DELETE QUERY
        SQLiteDatabase db = sqLite.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM tiket WHERE id = ?");
        stmt.bindLong(1, data.getId());
        stmt.executeUpdateDelete();
    }

    private void pesanKursi(Kursi data) {
        // get logged in username that saved in shared preference
        SharedPreference sharedPreference = new SharedPreference();
//        final String username = sharedPreference.getValue(getApplicationContext(), "username");
        final String username = "18101080";

        // do SQL INSERT QUERY
        SQLiteDatabase db = sqLite.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO tiket (id_film, nomor_seat, status, created_by) VALUES (?, ?, ?, ?)");
        stmt.bindLong(1, data.getIdFilm());
        stmt.bindString(2, data.getNomorSeat());
        stmt.bindLong(3, 1);
        stmt.bindString(4, username);
        stmt.executeInsert();
    }

    @SuppressLint("DefaultLocale")
    private ArrayList<Kursi> loadDaftarKursi() {
        ArrayList<Kursi> results = new ArrayList<>();
        String[] daftarKursi = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
                                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
                                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8",
                                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8",
                                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8",
                                "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8",};

        SQLiteDatabase db = sqLite.getReadableDatabase();
        Cursor cursor;

        for (String seat : daftarKursi) {
            cursor = db.rawQuery(String.format("SELECT * FROM tiket WHERE id_film = %d AND nomor_seat = \"%s\" LIMIT 1", idFilm, seat), null);

            if (cursor.getCount() == 0) {
                Kursi row = new Kursi();
                row.setId(0);
                row.setIdFilm(idFilm);
                row.setNomorSeat(seat);
                row.setStatus(0);
                row.setCreatedBy("");

                results.add(row);
            } else {
                cursor.moveToFirst();

                Kursi row = new Kursi();
                row.setId(cursor.getInt(0));
                row.setIdFilm(cursor.getInt(1));
                row.setNomorSeat(cursor.getString(2));
                row.setStatus(cursor.getInt(3));
                row.setCreatedBy(cursor.getString(4));

                results.add(row);
            }

            cursor.close();
        }

        return results;
    }
}
