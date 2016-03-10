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
public class WoDeLiCaiActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout xiaofei;
    private RelativeLayout chongzhi;
    private RelativeLayout tixian;
    private RelativeLayout zhanghu;
    private RelativeLayout touzi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wodelicai);
        xiaofei = (RelativeLayout) findViewById(R.id.xiaofeijilu);
        chongzhi = (RelativeLayout) findViewById(R.id.chongzhijilu);
        tixian = (RelativeLayout) findViewById(R.id.tixianjilu);
        zhanghu = (RelativeLayout) findViewById(R.id.zhanghuzichan);
        touzi = (RelativeLayout) findViewById(R.id. touzishouyi);
        xiaofei.setOnClickListener(this);
        chongzhi.setOnClickListener(this);
        tixian.setOnClickListener(this);
        zhanghu.setOnClickListener(this);
        touzi.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xiaofeijilu:
                Intent intent = new Intent(this,XiaoFeiJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.chongzhijilu:
                Intent intent1 = new Intent(this,ChongZhiActivity.class);
                startActivity(intent1);
                break;
            case R.id.tixianjilu:
                Intent intent2 = new Intent(this,TiXianActivity.class);
                startActivity(intent2);
                break;
            case R.id.zhanghuzichan:
                Intent intent3 = new Intent(this,ZhangHuZiChanActivity.class);
                startActivity(intent3);
                break;
            case R.id.touzishouyi:
                Intent intent4 = new Intent(this,TouZiShouYiActivity.class);
                startActivity(intent4);
                break;
        }

    }
}
