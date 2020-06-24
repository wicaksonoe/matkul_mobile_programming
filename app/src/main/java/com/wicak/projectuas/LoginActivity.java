package com.wicak.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText input_username;
    private TextInputEditText input_password;
    private TextInputLayout layout_username;
    private TextInputLayout layout_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);

        layout_username = findViewById(R.id.layout_username);
        layout_password = findViewById(R.id.layout_password);
    }

    public void login(View view) {
        layout_username.setErrorEnabled(false);
        layout_password.setErrorEnabled(false);

        final String username = input_username.getText().toString();
        final String password = input_password.getText().toString();

        if (username.equals("18101080") && password.equals("18101080")) {
            Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_LONG).show();

            // TODO: go to main menu
            Intent intentMainMenu = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentMainMenu);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Kombinasi username dan password salah", Toast.LENGTH_LONG).show();

            if (!username.equals("18101080")) {
                layout_username.setError("Username salah. 18101080");
            }

            if (!password.equals("18101080")) {
                layout_password.setError("Password salah. 18101080");
            }
        }
    }
}
