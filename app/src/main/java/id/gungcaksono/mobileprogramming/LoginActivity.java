package id.gungcaksono.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText textEmail;
    private EditText textPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        textEmail = (EditText) findViewById(R.id.textEmail);
        textPassword = (EditText) findViewById(R.id.textPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(v);
            }
        });
    }

    public void checkLogin(View arg0) {
        final String email = textEmail.getText().toString();
        final String password = textPassword.getText().toString();

        boolean emailAccepted = isValidEmail(email);
        boolean passwordAccepted = isValidPassword(password);

        if(!emailAccepted) {
            textEmail.setError("Invalid email");
        }

        if(!passwordAccepted) {
            textPassword.setError("Password must be at least 8 characters");
        }

        if (emailAccepted && passwordAccepted) {
            Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_LONG).show();

            Intent moveToMainMenu = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(moveToMainMenu);
            finish();
        }

    }

    public boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }
}
