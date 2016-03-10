package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class XfbShuoMingActivity extends BaseActivity{
    private TextView xuefeibao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuefeishuoming);
        xuefeibao = (TextView) findViewById(R.id.xuefeibaoshuoming);
        Intent intent = getIntent();
        String data = intent.getStringExtra("shuoming");
        xuefeibao.setText(data);
    }
}
