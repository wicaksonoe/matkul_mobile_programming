package com.wicak.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FilmTambahActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    String waktu_tayang = String.format("%02d", hour) + ":" + String.format("%02d", minute);

    SQLite dbHelper = new SQLite(FilmTambahActivity.this);
    TextInputLayout layoutTambahJudul;
    TextInputLayout layoutTambahHarga;

    TextInputEditText inputTambahJudul;
    TextInputEditText inputTambahHarga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_tambah);

        getSupportActionBar().setTitle("Tambah Film Baru");

        layoutTambahJudul = findViewById(R.id.layout_tambah_judul_film);
        layoutTambahHarga = findViewById(R.id.layout_tambah_harga);

        inputTambahJudul = findViewById(R.id.input_tambah_judul_film);
        inputTambahHarga = findViewById(R.id.input_tambah_harga);

        Button buttonSetTime = (Button) findViewById(R.id.button_set_waktu_tayang);
        buttonSetTime.setText(this.waktu_tayang);
        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Waktu Tayang");
            }
        });

        Button buttonSubmit = (Button) findViewById(R.id.button_tambah_film);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahFilm();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Button buttonSetTime = (Button) findViewById(R.id.button_set_waktu_tayang);
        waktu_tayang = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        buttonSetTime.setText(waktu_tayang);
    }

    public void tambahFilm() {
        // initial variable
        String judul_film = inputTambahJudul.getText().toString();
        String harga_tiket = inputTambahHarga.getText().toString();

        // get logged in username that saved in shared preference
        SharedPreference sharedPreference = new SharedPreference();
//        final String username = sharedPreference.getValue(getApplicationContext(), "username");
        final String username = "18101080";

        // do SQL INSERT QUERY
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO film (judul_film, waktu_tayang, harga, created_by) VALUES (?, ?, ?, ?)");
        stmt.bindString(1, judul_film);
        stmt.bindString(2, waktu_tayang);
        stmt.bindDouble(3, Double.parseDouble(harga_tiket));
        stmt.bindString(4, username);
        stmt.executeInsert();

        // show toast success, then refresh list and close this activity
        Toast.makeText(getApplicationContext(), "Film baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();

        // close this activity with result to trigger void
        Intent returnToFilmDaftar = new Intent();
        setResult(RESULT_OK, returnToFilmDaftar);
        finish();
    }
}
