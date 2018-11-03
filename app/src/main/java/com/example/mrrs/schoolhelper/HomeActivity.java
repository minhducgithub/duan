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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrrs.schoolhelper.model.Student;
import com.example.mrrs.schoolhelper.service.APIService;
import com.example.mrrs.schoolhelper.service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private LinearLayout info, contact, service, schedule, attendance;
    public static final int LOCATION_PERMISSION = 99;
    private ImageView home_imv_user;
    private TextView home_txt_block, home_txt_course, home_txt_name, home_txt_code;
    int indexStudent = InfoActivity.indexStudent;
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
        GetDataInfoHome();
    }

    private void GetDataInfoHome() {
        Dataservice dataservice = APIService.getService();
        Call<List<Student>> callback = dataservice.GetDataInfoStudent();
        callback.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                ArrayList<Student> infoStudent = (ArrayList<Student>) response.body();
                try{
                    Picasso.with(getApplicationContext()).load(infoStudent.get(indexStudent).getSvhinh()).into(home_imv_user);
                    home_txt_name.setText(infoStudent.get(indexStudent).getSvten());
                    home_txt_block.setText(infoStudent.get(indexStudent).getSvblock());
                    home_txt_code.setText(infoStudent.get(indexStudent).getSvcode());
                    home_txt_course.setText(infoStudent.get(indexStudent).getSvcourse());
                }catch (Exception e){
                    Toast.makeText(HomeActivity.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(HomeActivity.this, "this is an actual network failure!", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(HomeActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
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
        home_imv_user = findViewById(R.id.home_avt_user);
        home_txt_name = findViewById(R.id.home_lbl_name);
        home_txt_block = findViewById(R.id.home_txt_block);
        home_txt_course = findViewById(R.id.home_txt_course);
        home_txt_code = findViewById(R.id.home_txt_code);
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
                changeScreen(HomeActivity.this, NewsActivity.class);
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
