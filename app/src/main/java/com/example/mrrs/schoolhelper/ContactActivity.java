package com.example.mrrs.schoolhelper;

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


public class ContactActivity extends AppCompatActivity {
    EditText edt_title,edt_content;
    Button btn_submit;
    String title, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_contact);
        Toolbar mToolbar = findViewById(R.id.contact_toolbar);
        mToolbar.setTitle(getString(R.string.contact));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactActivity.super.onBackPressed();
            }
        });
        AnhXa();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edt_title.getText().toString();
                content = edt_content.getText().toString();
                if(title.isEmpty()||content.isEmpty()){
                    Toast.makeText(ContactActivity.this, "Not fully entered", Toast.LENGTH_SHORT).show();
                }else {
                    SubmitFeedBack(APIConnect.URL_FEEDBACK);
                }
            }
        });
    }

    private void AnhXa() {
        edt_title = findViewById(R.id.contact_edt_title);
        edt_content = findViewById(R.id.contact_edt_content);
        btn_submit = findViewById(R.id.contact_btn_submit);
    }
    private void SubmitFeedBack (String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("OK")){
                            Toast.makeText(ContactActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            edt_title.setText("");
                            edt_content.setText("");
                        }else {
                            Toast.makeText(ContactActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactActivity.this, "Error: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title",title);
                params.put("content",content);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
