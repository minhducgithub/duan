package com.example.mrrs.schoolhelper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mrrs.schoolhelper.adapter.Attendance_GenreAdapter;
import com.example.mrrs.schoolhelper.model.Attendance;
import com.example.mrrs.schoolhelper.model.AttendanceModel;
import com.example.mrrs.schoolhelper.model.LocationMap;
import com.example.mrrs.schoolhelper.model.Student;
import com.example.mrrs.schoolhelper.service.APIService;
import com.example.mrrs.schoolhelper.service.Dataservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AttendanceActivity extends AppCompatActivity {
    //    Variables
    private RecyclerView mRecyclerView;
    private List<Genre_Attendance> genres = new ArrayList<>();
    private Attendance_GenreAdapter mAdapter;
    private ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_attendance);
        //Handle toolbar
        Toolbar mToolbar = findViewById(R.id.attendance_toolbar);
        mToolbar.setTitle(getString(R.string.attendance));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttendanceActivity.super.onBackPressed();
            }
        });
        // Assign componentr for expandable for recycler view
        mRecyclerView = findViewById(R.id.attendance_recycler_view);
        GetDataAttendance();
    }
    private void GetDataAttendance() {
        Dataservice dataservice = APIService.getService();
        Call<List<Attendance>> callback = dataservice.GetDataAttendance();
        callback.enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                attendanceArrayList = (ArrayList<Attendance>) response.body();
                for(int j = 0; j <attendanceArrayList.size();j++){
                    title = attendanceArrayList.get(j).getMon();
                    genres.add(new Genre_Attendance(title,attendanceArrayList));
                    mAdapter = new Attendance_GenreAdapter(genres);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(AttendanceActivity.this, "this is an actual network failure!", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    Intent numbersIntent = new Intent(AttendanceActivity.this, HomeActivity.class);
                    startActivity(numbersIntent);
                }
                else {
                    Toast.makeText(AttendanceActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }
//    private void getGenres() {
//        attendanceArrayList.add(new Attendance("Thaitt"));
//        genres.add(new Genre_Attendance(tenGiangVien, attendanceArrayList));
//
//        mAdapter = new Attendance_GenreAdapter(genres);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
//        mRecyclerView.setAdapter(mAdapter);
//    }
}
