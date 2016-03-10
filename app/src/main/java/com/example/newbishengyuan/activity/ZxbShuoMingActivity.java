package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class ZxbShuoMingActivity extends BaseActivity{
    private TextView zhuxuebao;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuxuebaoshuoming);
        zhuxuebao = (TextView) findViewById(R.id.zhuxuebaoshuoming);
        Intent intent = getIntent();
        String data = intent.getStringExtra("shuoming");
        zhuxuebao.setText(data);
    }
}
