package id.gungcaksono.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonProfile;
    private Button buttonSchedule;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);

        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonSchedule = (Button) findViewById(R.id.buttonSchedule);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonProfile.setOnClickListener(this);
        buttonSchedule.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonProfile:
                Intent moveToProfile = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(moveToProfile);
                break;

            case R.id.buttonSchedule:
                Intent moveToSchedule = new Intent(MenuActivity.this, ScheduleActivity.class);
                startActivity(moveToSchedule);
                break;

            case R.id.buttonLogout:
                Toast.makeText(getApplicationContext(), "Logout!\nTerima kasih sudah berkunjung.", Toast.LENGTH_LONG).show();

                Intent moveToLogin = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(moveToLogin);
                finish();
                break;
        }
    }
}
