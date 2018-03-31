package com.example.user.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CollegeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_info);

    }

    public void openPlacement(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.ac.in/highest-package.html");
    }
    public void openTpp(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.edu.in/360-degree-training");
    }
    public void openalumini(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.edu.in/alumni");
    }

    public void Fees(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.ac.in/fee-structure.html");
    }
    public void Campus(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.edu.in/cgc_campus");
    }
    public void Contacts(View view){
        WebView webView=new WebView(this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("https://www.cgc.edu.in/contactus");
    }
}
