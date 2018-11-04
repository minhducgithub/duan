package com.example.mrrs.schoolhelper;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {
    WebView news_webview;
    WebSettings news_settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_news);
        Toolbar mToolbar = findViewById(R.id.news_toolbar);
        mToolbar.setTitle(getString(R.string.news));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsActivity.super.onBackPressed();
            }
        });
        try{
            news_webview = (WebView) findViewById(R.id.news_webviews);
            news_webview.loadUrl("http://ap.poly.edu.vn/news/home.php");
            news_webview.setWebViewClient(new WebViewClient());
            news_settings = news_webview.getSettings();
            news_settings.setJavaScriptEnabled(true);
        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (news_webview.canGoBack()){
            news_webview.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
