package com.wicak.hitunggeometri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getSupportActionBar().setTitle("Main Menu");
    }

    public void hitung_segitiga(View view) {
        // TODO: Navigate to hitung segitiga activity
        Intent intentHitungSegitiga = new Intent(MainMenu.this, HitungSegitga.class);
        startActivity(intentHitungSegitiga);
    }

    public void hitung_lingkarang(View view) {
        // TODO: Navigate to hitung lingkaran activity
        Intent intentHitungLingkaran = new Intent(MainMenu.this, HitungLingkaran.class);
        startActivity(intentHitungLingkaran);
    }

    public void hitung_kubus(View view) {
        // TODO: Navigate to hitung kubus activity
        Intent intentHitungKubus = new Intent(MainMenu.this, HitungKubus.class);
        startActivity(intentHitungKubus);
    }

}
