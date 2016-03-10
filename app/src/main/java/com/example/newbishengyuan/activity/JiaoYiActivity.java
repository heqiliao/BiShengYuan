package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newbishengyuan.R;


/**
 * Created by 何弃疗 on 2015/8/4.
 */
public class JiaoYiActivity extends Activity implements View.OnClickListener{
    private TextView wancheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiaoyixiangqing);
        wancheng = (TextView) findViewById(R.id.wancheng);
        wancheng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wancheng:
                Intent intent = new Intent(this,SumActivity.class);
                startActivity(intent);
                break;
        }

    }
}
