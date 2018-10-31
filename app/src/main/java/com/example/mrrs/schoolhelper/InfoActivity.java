package com.example.mrrs.schoolhelper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
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

public class InfoActivity extends AppCompatActivity {
    TextView txt_block,txt_ten,txt_code,txt_course,txt_status
            ,txt_phone,txt_email,txt_address,txt_specialized,txt_start;
    ImageView imv_userstudent;
    public static final int indexStudent = 0;
    ArrayList<Student> infoStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_info);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.info_toolbar);
        mToolbar.setTitle(getString(R.string.info));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoActivity.super.onBackPressed();
            }
        });
        AnhXa();
        GetData();

    }

    private void AnhXa() {
        imv_userstudent = findViewById(R.id.imv_userstudent);
        txt_ten = findViewById(R.id.service_user_name);
        txt_block = findViewById(R.id.service_user_block);
        txt_code = findViewById(R.id.service_user_code);
        txt_course = findViewById(R.id.service_user_course);
        txt_status = findViewById(R.id.service_user_status);
        txt_phone = findViewById(R.id.service_user_phone);
        txt_email = findViewById(R.id.service_user_email);
        txt_address = findViewById(R.id.service_user_address);
        txt_specialized = findViewById(R.id.service_user_specialized);
        txt_start = findViewById(R.id.service_user_start);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Student>> callback = dataservice.GetDataInfoStudent();
        callback.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                infoStudent = (ArrayList<Student>) response.body();
                try{
                    Picasso.with(getApplicationContext()).load(infoStudent.get(indexStudent).getSvhinh()).into(imv_userstudent);
                    txt_ten.setText(infoStudent.get(indexStudent).getSvten());
                    txt_block.setText(infoStudent.get(indexStudent).getSvblock());
                    txt_code.setText(infoStudent.get(indexStudent).getSvcode());
                    txt_course.setText(infoStudent.get(indexStudent).getSvcourse());
                    txt_status.setText(infoStudent.get(indexStudent).getSvstatus());
                    txt_phone.setText(infoStudent.get(indexStudent).getSvphone());
                    txt_email.setText(infoStudent.get(indexStudent).getSvemail());
                    txt_address.setText(infoStudent.get(indexStudent).getSvaddress());
                    txt_specialized.setText(infoStudent.get(indexStudent).getSvspecialized());
                    txt_start.setText(infoStudent.get(indexStudent).getSvstart());
                }catch (Exception e){
                    Toast.makeText(InfoActivity.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(InfoActivity.this, "this is an actual network failure!", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    Intent numbersIntent = new Intent(InfoActivity.this, HomeActivity.class);
                    startActivity(numbersIntent);
                }
                else {
                    Toast.makeText(InfoActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }
}
