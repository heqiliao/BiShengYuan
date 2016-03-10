package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class CourierActivity extends BaseActivity {
    private WebView webView;
    private String courierUrl = "http://m.kuaidi100.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuaidichaxun);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(courierUrl);
    }
}
