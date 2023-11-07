package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Govt extends AppCompatActivity {
    WebView myWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.govt);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://agricoop.nic.in/en/Major#gsc.tab=0");
    }
}
