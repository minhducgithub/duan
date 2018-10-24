package com.example.mrrs.schoolhelper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mrrs.schoolhelper.welcome.WelcomeActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private LinearLayout info, contact, service, schedule, attendance;
    public static final int LOCATION_PERMISSION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);
        // Define toolbar
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        // Create object to run check permission function
        CheckPermission checkPermission = new CheckPermission();
        //        Check location permission
        if (checkPermission.checkPermission(HomeActivity.this)) {
            // Permission granted
        } else {
            // Permission not granted
            checkPermission.requestPermission(HomeActivity.this, LOCATION_PERMISSION);
        }
        initComponent();
        setOnClickListenerForComponent();
    }

    private void setOnClickListenerForComponent() {
        info.setOnClickListener(this);
        contact.setOnClickListener(this);
        service.setOnClickListener(this);
        schedule.setOnClickListener(this);
        attendance.setOnClickListener(this);
    }

    private void initComponent() {
        info = findViewById(R.id.home_option_info);
        contact = findViewById(R.id.home_option_contact);
        service = findViewById(R.id.home_option_Service);
        schedule = findViewById(R.id.home_option_schedule);
        attendance = findViewById(R.id.home_option_attendance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_option_info:
                Log.d("Clicked", "Info");
                changeScreen(HomeActivity.this, InfoActivity.class);
                break;
            case R.id.home_option_contact:
                Log.d("Clicked", "Contact");
                changeScreen(HomeActivity.this, ContactActivity.class);
                break;
            case R.id.home_option_Service:
                Log.d("Clicked", "Service");
                changeScreen(HomeActivity.this, ServiceActivity.class);
                break;
            case R.id.home_option_schedule:
                Log.d("Clicked", "Schedule");
                changeScreen(HomeActivity.this, ScheduleActivity.class);
                break;
            case R.id.home_option_attendance:
                Log.d("Clicked", "Attendance");
                changeScreen(HomeActivity.this, AttendanceActivity.class);
                break;
            default:
                Log.d("Clicked", "Default: " + v.getId() + "");
                break;
        }
    }

    private int pressed = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // do something here
                if (pressed < 1) {
                    Toast.makeText(HomeActivity.this, "Press again to exit app", Toast.LENGTH_SHORT).show();
                    ++pressed;
                } else {
                    pressed = 0;
                    finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void changeScreen(Context currentContext, Class target) {
        Intent i = new Intent(currentContext, target);
        startActivity(i);
    }
}
