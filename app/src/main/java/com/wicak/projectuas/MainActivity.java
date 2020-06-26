package com.wicak.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
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
