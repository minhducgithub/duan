package com.example.mrrs.schoolhelper;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrrs.schoolhelper.model.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.APIRetrofitClient;
import service.APIService;
import service.Dataservice;

public class InfoActivity extends AppCompatActivity {
    TextView txt_block,txt_ten,txt_code,txt_course,txt_status
            ,txt_phone,txt_email,txt_address,txt_specialized,txt_start;
    ImageView imv_userstudent;
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
                ArrayList<Student> info = (ArrayList<Student>) response.body();
                Picasso.with(getApplicationContext()).load(info.get(0).getSvhinh()).into(imv_userstudent);
                txt_ten.setText(info.get(0).getSvten());
                txt_block.setText(info.get(0).getSvblock());
                txt_code.setText(info.get(0).getSvcode());
                txt_course.setText(info.get(0).getSvcourse());
                txt_status.setText(info.get(0).getSvstatus());
                txt_phone.setText(info.get(0).getSvphone());
                txt_email.setText(info.get(0).getSvemail());
                txt_address.setText(info.get(0).getSvaddress());
                txt_specialized.setText(info.get(0).getSvspecialized());
                txt_start.setText(info.get(0).getSvstart());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {

            }
        });
    }
}
