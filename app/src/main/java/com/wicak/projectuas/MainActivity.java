package com.wicak.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView btnManageSeat, btnProfile;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        btnManageSeat = findViewById(R.id.btn_manage_seat);
        btnManageSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKelolaKursi = new Intent(MainActivity.this, KursiMainActivity.class);
                startActivity(intentKelolaKursi);
            }
        });

        btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentProfile);
            }
        });
    }

    public void logout(View view) {
        SharedPreference sharedPreference = new SharedPreference();

        final String username = sharedPreference.getValue(getApplicationContext(), "username");
        Toast.makeText(getApplicationContext(), "Berhasil Logout\nSampai jumpa lagi " + username, Toast.LENGTH_LONG).show();

        // TODO: clear all shared preferences
        sharedPreference.clearSharedPreference(getApplicationContext());

        // TODO: navigate to login activity
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    public void navigateToFilm(View view) {
        Intent intentDaftarFilm = new Intent(MainActivity.this, FilmDaftarActivity.class);
        startActivity(intentDaftarFilm);
    }
}
