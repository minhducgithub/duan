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

import com.example.mrrs.schoolhelper.welcome.PrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button forgotPassword, btn_login;
    PrefManager prefManager;

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
                changeScreen(LoginActivity.this, HomeActivity.class);
                // set for auto next screen if login yet
                prefManager.setFirstTimeLogin(false);
                break;
        }
    }

    private void changeScreen(Context currentContext, Class target) {
        Intent i = new Intent(currentContext, target);
        startActivity(i);
    }
}
