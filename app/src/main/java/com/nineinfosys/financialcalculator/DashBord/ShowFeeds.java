package com.nineinfosys.financialcalculator.DashBord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nineinfosys.financialcalculator.R;

public class ShowFeeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feeds);

        WebView w1 = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings webSettings = w1.getSettings();
        webSettings.setJavaScriptEnabled(false);
        w1.loadUrl(url);

    }
}
