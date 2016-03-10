package com.example.newbishengyuan.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/8/6.
 */
public class GongJiaoActivity extends BaseActivity {
    private WebView webView;
    private String gongjiaoUrl = "http://m.8684.cn/wuhan_bus";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gongjiao);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(gongjiaoUrl);
    }
}