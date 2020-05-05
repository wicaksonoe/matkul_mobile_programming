package com.wicak.hitunggeometri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class HitungLingkaran extends AppCompatActivity {
    private TextView textViewLingkaranStatus;
    private TextView textViewLingkaranHasil;
    private TextInputEditText editTextJari;
    private TextInputLayout layoutJari;
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Hitung Luas/Keliling Lingkaran");

        setContentView(R.layout.activity_hitung_lingkaran);

        textViewLingkaranStatus = (TextView) findViewById(R.id.lingkaran_status);
        textViewLingkaranHasil = (TextView) findViewById(R.id.lingkaran_hasil);
        editTextJari = (TextInputEditText) findViewById(R.id.input_jari);
        layoutJari = (TextInputLayout) findViewById(R.id.layout_jari);
    }

    public void hitung_luas(View view) {
        layoutJari.setErrorEnabled(false);
        String inputJari = editTextJari.getText().toString();
        if (inputJari.equals("")) {
            layoutJari.setError("Panjang jari-jari tidak boleh kosong");
            return;
        }

        double luas;
        double jari = Double.parseDouble(inputJari);

        luas = 3.14 * jari * jari;

        textViewLingkaranStatus.setText("Luas Lingkaran");
        textViewLingkaranHasil.setText(df.format(luas) + " cm^2");
    }

    public void hitung_keliling(View view) {
        layoutJari.setErrorEnabled(false);
        String inputJari = editTextJari.getText().toString();
        if (inputJari.equals("")) {
            layoutJari.setError("Panjang jari-jari tidak boleh kosong");
            return;
        }

        double keliling;
        double jari = Double.parseDouble(inputJari);

        keliling = 3.14 * (2 * jari);

        textViewLingkaranStatus.setText("Keliling Lingkaran");
        textViewLingkaranHasil.setText(df.format(keliling) + " cm");
    }
}
