package com.example.mrrs.schoolhelper;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mrrs.schoolhelper.adapter.Attendance_GenreAdapter;
import com.example.mrrs.schoolhelper.model.AttendanceModel;

import java.util.ArrayList;
import java.util.List;


public class AttendanceActivity extends AppCompatActivity {
    //    Variables
    private RecyclerView mRecyclerView;
    private List<Genre_Attendance> genres;
    private Attendance_GenreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_attendance);
        //Handle toolba
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
        getGenres();
        mAdapter = new Attendance_GenreAdapter(genres);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getGenres() {
        genres = new ArrayList<>(6);
        List<AttendanceModel> list = null;
        // Add item in expandable
        for (int i = 0; i < 6; i++) {
            list = new ArrayList<AttendanceModel>(3);
            list.add(new AttendanceModel("Paramore"));
            list.add(new AttendanceModel("Mrrs"));
            list.add(new AttendanceModel("Thanh Tam"));
            list.add(new AttendanceModel("NGuyen"));
            //Add subject
            genres.add(new Genre_Attendance("Subject " + i, list));
        }
    }
}
