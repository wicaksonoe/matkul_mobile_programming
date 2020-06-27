package com.wicak.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FilmEditActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public static final String ID_FILM = "id_film";
    public static final String JUDUL_FILM = "judul_film";
    public static final String HARGA_TIKET = "harga_tiket";
    public static final String WAKTU_TAYANG = "waktu_tayang";

    int id_film;
    float harga_tiket;
    String waktu_tayang, judul_film;

    TextInputLayout layoutUpdateJudulFilm;
    TextInputLayout layoutUpdateHargaTiket;
    TextInputEditText inputUpdateJudulFilm;
    TextInputEditText inputUpdateHargaTiket;

    Button btnUpdate;
    Button btnSetWaktuTayang;

    SQLite dbHelper = new SQLite(FilmEditActivity.this);

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_film_edit);

        id_film = getIntent().getIntExtra(ID_FILM, 0);
        judul_film = getIntent().getStringExtra(JUDUL_FILM);
        waktu_tayang = getIntent().getStringExtra(WAKTU_TAYANG);
        harga_tiket = getIntent().getFloatExtra(HARGA_TIKET, 0);

        getSupportActionBar().setTitle("Edit Film " + judul_film);

        layoutUpdateJudulFilm = (TextInputLayout) findViewById(R.id.layout_update_judul_film);
        inputUpdateJudulFilm = (TextInputEditText) findViewById(R.id.input_update_judul_film);
        layoutUpdateHargaTiket = (TextInputLayout) findViewById(R.id.layout_update_harga);
        inputUpdateHargaTiket = (TextInputEditText) findViewById(R.id.input_update_harga);
        btnUpdate = (Button) findViewById(R.id.button_update_film);
        btnSetWaktuTayang = (Button) findViewById(R.id.button_set_waktu_tayang);

        inputUpdateJudulFilm.setText(judul_film);
        inputUpdateHargaTiket.setText(Float.toString(harga_tiket));
        btnSetWaktuTayang.setText(waktu_tayang);

        btnSetWaktuTayang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Waktu Tayang");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFilm();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        waktu_tayang = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        btnSetWaktuTayang.setText(waktu_tayang);
    }

    private void updateFilm() {
        String username = "18101080";
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement update_film = db.compileStatement("UPDATE film SET judul_film = ?, waktu_tayang = ?, harga = ?, created_by = ? WHERE id = ?");
        update_film.bindString(1, inputUpdateJudulFilm.getText().toString());
        update_film.bindString(2, waktu_tayang);
        update_film.bindDouble(3, Double.parseDouble(inputUpdateHargaTiket.getText().toString()));
        update_film.bindString(4, username);
        update_film.bindString(5, String.valueOf(id_film));
        update_film.executeUpdateDelete();

        // close this activity with result to trigger void
        Intent returnToFilmDaftar = new Intent();
        setResult(RESULT_OK, returnToFilmDaftar);
        finish();
    }
}
