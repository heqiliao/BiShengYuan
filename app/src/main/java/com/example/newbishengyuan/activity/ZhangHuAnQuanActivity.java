package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/26.
 */
public class ZhangHuAnQuanActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout shoujirenzheng;
    private RelativeLayout shimingrenzheng;
    private RelativeLayout denglumima;
    private RelativeLayout zhifumima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhanghuanquan);
        shoujirenzheng = (RelativeLayout) findViewById(R.id.shoujirenzheng);
        shimingrenzheng = (RelativeLayout) findViewById(R.id.shimingrenzheng);
        denglumima = (RelativeLayout) findViewById(R.id.denglumima);
        zhifumima = (RelativeLayout) findViewById(R.id.zhifumima);
        shoujirenzheng.setOnClickListener(this);
        shimingrenzheng.setOnClickListener(this);
        denglumima.setOnClickListener(this);
        zhifumima.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shoujirenzheng:
                Intent intent = new Intent(ZhangHuAnQuanActivity.this,ShouJiRenZhengActivity.class);
                startActivity(intent);
                break;
            case R.id.shimingrenzheng:
                Intent intent1 = new Intent(ZhangHuAnQuanActivity.this,ShiMingRenZhengActivity.class);
                startActivity(intent1);
                break;
            case R.id.denglumima:
                Intent intent2 = new Intent(ZhangHuAnQuanActivity.this,DengLuMiMaActivity.class);
                startActivity(intent2);
                break;
            case R.id.zhifumima:
                Intent intent3 = new Intent(ZhangHuAnQuanActivity.this,ZhiFuRenZhengActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
