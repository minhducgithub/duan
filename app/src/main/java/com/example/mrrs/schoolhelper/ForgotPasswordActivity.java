package com.example.mrrs.schoolhelper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrrs.schoolhelper.service.APIConnect;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {


    EditText edt_forgotemail;
    Button btnsubmitReset;
    String forgotemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_forgot_password);
        // Define toolbar
        Toolbar toolbar = findViewById(R.id.forgotpass_toolbar);
        setSupportActionBar(toolbar);
        // Call backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        Anhxa();
        btnsubmitReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotemail = edt_forgotemail.getText().toString();
                if (forgotemail.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Not fully entered!", Toast.LENGTH_SHORT).show();
                }else {
                    checkEmail(forgotemail);
                }
            }

        });
    }
    private void checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (matcher.find()) {
            SubmitForgotPassword(APIConnect.URL_FORGOT);
        } else {
            Toast.makeText(ForgotPasswordActivity.this, "Email invalidate!", Toast.LENGTH_SHORT).show();
        }
    }
    private void SubmitForgotPassword(String urlForgot) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForgot,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("OK")){
                            Toast.makeText(ForgotPasswordActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            edt_forgotemail.setText("");

                        }else {
                            Toast.makeText(ForgotPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("email",forgotemail);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {
        edt_forgotemail = (EditText) findViewById(R.id.forgot_edt_email);
        btnsubmitReset =(Button) findViewById(R.id.forgot_btn_submit);
    }
}
