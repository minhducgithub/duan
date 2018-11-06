package com.example.mrrs.schoolhelper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrrs.schoolhelper.model.Login;
import com.example.mrrs.schoolhelper.service.APIService;
import com.example.mrrs.schoolhelper.service.Dataservice;
import com.example.mrrs.schoolhelper.welcome.PrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button forgotPassword, btn_login;
    PrefManager prefManager;
    EditText edt_user, edt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        initComponent();
        setOnClickListener();
        prefManager = new PrefManager(LoginActivity.this);
        if (!prefManager.isFirstTimeLogin()) {
            changeScreen(LoginActivity.this, HomeActivity.class);
            finish();
        }
    }

    public void initComponent() {
        forgotPassword = findViewById(R.id.login_btn_forgot);
        btn_login = findViewById(R.id.login_btn_login);
        edt_user = findViewById(R.id.login_edt_username);
        edt_pass = findViewById(R.id.login_edt_pass);
    }

    public void setOnClickListener() {
        forgotPassword.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_forgot:
                changeScreen(LoginActivity.this, ForgotPasswordActivity.class);
                break;
            case R.id.login_btn_login:
                // set for auto next screen if login yet
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();
                if(user.length() > 0 && pass.length() >0){
                    Dataservice dataservice = APIService.getService();
                    Call<List<Login>> callback = dataservice.Login(user,pass);
                    callback.enqueue(new Callback<List<Login>>() {
                        @Override
                        public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                            ArrayList<Login> loginArrayList = (ArrayList<Login>) response.body();
                            if (loginArrayList.size() > 0) {
                                changeScreen(LoginActivity.this, HomeActivity.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Login>> call, Throwable t) {
                            if (t instanceof IOException) {
                                Toast.makeText(LoginActivity.this, "this is an actual network failure!", Toast.LENGTH_SHORT).show();
                                // logging probably not necessary
                            } else {
                                Toast.makeText(LoginActivity.this, "The account or password is incorrect!", Toast.LENGTH_SHORT).show();
                                // todo log to some central bug tracking service
                            }
                        }
                    });
                }else{
                    Toast.makeText(this, "Not fully entered", Toast.LENGTH_SHORT).show();
                }
                prefManager.setFirstTimeLogin(false);
                break;
        }
    }

    private void changeScreen(Context currentContext, Class target) {
        Intent i = new Intent(currentContext, target);
        startActivity(i);
    }
}
