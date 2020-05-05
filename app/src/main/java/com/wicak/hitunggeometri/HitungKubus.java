package com.wicak.hitunggeometri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class HitungKubus extends AppCompatActivity {
    private TextView textViewKubusStatus;
    private TextView textViewKubusHasil;
    private TextInputEditText editTextRusuk;
    private TextInputLayout layoutRusuk;
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Hitung Volume Kubus");

        setContentView(R.layout.activity_hitung_kubus);

        editTextRusuk = (TextInputEditText) findViewById(R.id.input_rusuk);
        layoutRusuk = (TextInputLayout) findViewById(R.id.layout_rusuk);

        textViewKubusStatus = (TextView) findViewById(R.id.kubus_status);
        textViewKubusHasil = (TextView) findViewById(R.id.kubus_hasil);
    }

    public void hitung_volume(View view) {
        layoutRusuk.setErrorEnabled(false);
        String inputRusuk = editTextRusuk.getText().toString();
        if (inputRusuk.equals("")) {
            layoutRusuk.setError("Panjang rusuk harus diisi");
            return;
        }

        double volume;
        double rusuk = Double.parseDouble(inputRusuk);

        volume = rusuk * rusuk * rusuk;

        textViewKubusStatus.setText("Volume Kubus");
        textViewKubusHasil.setText(df.format(volume) + " cm^3");
    }
}
