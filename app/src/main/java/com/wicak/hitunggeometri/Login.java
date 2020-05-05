package com.wicak.hitunggeometri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private TextInputEditText textUsername;
    private TextInputEditText textPassword;
    private TextInputLayout layoutUsername;
    private TextInputLayout layoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        textUsername = (TextInputEditText) findViewById(R.id.input_username);
        textPassword = (TextInputEditText) findViewById(R.id.input_password);

        layoutUsername = (TextInputLayout) findViewById(R.id.layout_username);
        layoutPassword = (TextInputLayout) findViewById(R.id.layout_password);
    }

    public void login(View view) {
        layoutUsername.setErrorEnabled(false);
        layoutPassword.setErrorEnabled(false);
        final String username = textUsername.getText().toString();
        final String password = textPassword.getText().toString();

        if (username.equals("18101080") && password.equals("18101080")) {
            Toast.makeText(getApplicationContext(), "Login sukses.", Toast.LENGTH_LONG).show();

            // TODO: go to main menu activity
            Intent intentMainMenu = new Intent(Login.this, MainMenu.class);
            startActivity(intentMainMenu);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Kombinasi username dan password salah.", Toast.LENGTH_LONG).show();

            if (!username.equals("18101080")) {
                layoutUsername.setError("Username salah. 18101080");
            }

            if (!password.equals("18101080")) {
                layoutPassword.setError("Password salah. 18101080");
            }
        }
    }
}
