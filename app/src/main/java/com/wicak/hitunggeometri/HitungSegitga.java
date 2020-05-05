package com.wicak.hitunggeometri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class HitungSegitga extends AppCompatActivity {
    private TextInputEditText textAlas;
    private TextInputEditText textTinggi;
    private TextInputLayout layoutAlas;
    private TextInputLayout layoutTinggi;
    private TextView textViewSegitigaStatus;
    private TextView textViewSegitigaHasil;
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Hitung Luas/Keliling Segitiga");

        setContentView(R.layout.activity_hitung_segitga);

        textAlas = (TextInputEditText) findViewById(R.id.input_alas);
        textTinggi = (TextInputEditText) findViewById(R.id.input_tinggi);

        layoutAlas = (TextInputLayout) findViewById(R.id.layout_alas);
        layoutTinggi = (TextInputLayout) findViewById(R.id.layout_tinggi);

        textViewSegitigaStatus = (TextView) findViewById(R.id.segitiga_status);
        textViewSegitigaHasil = (TextView) findViewById(R.id.segitiga_hasil);
    }

    public void hitung_luas(View view) {
        layoutAlas.setErrorEnabled(false);
        layoutTinggi.setErrorEnabled(false);
        String inputAlas = textAlas.getText().toString();
        String inputTingi = textTinggi.getText().toString();

        if (inputAlas.equals("")) {
            layoutAlas.setError("Panjang alas tidak boleh kosong");
            return;
        }
        if (inputTingi.equals("")) {
            layoutTinggi.setError("Panjang tinggi tidak boleh kosong");
            return;
        }

        double luas;
        final double alas = Double.parseDouble(inputAlas);
        final double tinggi = Double.parseDouble(inputTingi);

        luas = (alas * tinggi) / 2;

        textViewSegitigaStatus.setText("Luas Segitiga Siku-siku");
        textViewSegitigaHasil.setText(df.format(luas) + " cm^2");
    }

    public void hitung_keliling(View view) {
        layoutAlas.setErrorEnabled(false);
        layoutTinggi.setErrorEnabled(false);
        String inputAlas = textAlas.getText().toString();
        String inputTingi = textTinggi.getText().toString();

        if (inputAlas.equals("")) {
            layoutAlas.setError("Panjang alas tidak boleh kosong");
            return;
        }
        if (inputTingi.equals("")) {
            layoutTinggi.setError("Panjang tinggi tidak boleh kosong");
            return;
        }

        double sisiMiring;
        double keliling;
        final double alas = Double.parseDouble(inputAlas);
        final double tinggi = Double.parseDouble(inputTingi);

        sisiMiring = (alas * alas) + (tinggi * tinggi);
        sisiMiring = Math.sqrt(sisiMiring);

        keliling = alas + tinggi + sisiMiring;

        textViewSegitigaStatus.setText("Keliling Segitiga Siku-siku");
        textViewSegitigaHasil.setText(df.format(keliling) + " cm");
    }
}
