package com.example.newbishengyuan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Classification;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ������ on 2015/7/22.
 */
public class SumActivity extends BaseActivity implements View.OnClickListener{

    private TabHost tabHost;
    private TabWidget tabWidget;

    public TabHost getTabHost() {
        return tabHost;
    }


    private TextView bishengtongyue;
    private TextView zuorishouyi;
    private TextView yitouzijine;
    private TextView daifukuan;
    private TextView daishouhuo;
    private TextView daipingjia;
    private TextView reportText;
    private TextView title;
    private TextView date;
    private TextView content;
    private ImageView photo;
    private TextView shouyi;
    private TextView jine;


    private  String announcement;
    private String newsTitle;
    private String newsContent;
    private String newsTime;
    private String picture;
    private String balance;
    private String topay;
    private String yesterday_income;
    private String  finance_sum;
    private String toreceive;
    private String tocomment;

    private  MyApplication myApplication;
    private static String TAG = "CSID";

    @Override
    protected void onResume() {
        super.onResume();
//        initHomePage();
//        if(!TextUtils.isEmpty(myApplication.getCustomerId())){
//            queryPersonalCenter();
//        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sumtab);

        myApplication = (MyApplication) getApplication();

        bishengtongyue = (TextView) findViewById(R.id.bishengtongyue);
        zuorishouyi = (TextView) findViewById(R.id.zuorishouyi);
        yitouzijine = (TextView) findViewById(R.id.yitouzijine);
        daifukuan = (TextView) findViewById(R.id.daifukuan);
        daishouhuo = (TextView) findViewById(R.id.daishouhuo);
        daipingjia = (TextView) findViewById(R.id.daipingjia);
        reportText = (TextView) findViewById(R.id.reportText);
        title = (TextView) findViewById(R.id.id1);
        date = (TextView) findViewById(R.id.date);
        content = (TextView) findViewById(R.id.content);
        photo = (ImageView) findViewById(R.id.photo);
        shouyi = (TextView) findViewById(R.id.shouyi);
        jine = (TextView) findViewById(R.id.jine);


        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabWidget = (TabWidget) findViewById(android.R.id.tabs);
         tabHost.setup();

        View view1 = LayoutInflater.from(this).inflate(R.layout.view1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view2,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view3,null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.view4,null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.view5,null);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(view1).setContent(R.id.shouyeFragment));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(view2).setContent(R.id.licaiFragment));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(view3).setContent(R.id.shangchengFragment));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(view4).setContent(R.id.faxianFragment));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator(view5).setContent(R.id.wodeFragment));

        tabWidgetClicked();



    }



    private void tabWidgetClicked() {



        tabWidget.getChildAt(1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String customerId = myApplication.getCustomerId();
                if (TextUtils.isEmpty(customerId)) {
                    Intent intent = new Intent(SumActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    tabHost.setCurrentTab(1);

                }

            }

        });

        tabWidget.getChildAt(2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String customerId = myApplication.getCustomerId();
                if (TextUtils.isEmpty(customerId)) {
                    Intent intent = new Intent(SumActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    tabHost.setCurrentTab(2);

                }

            }

        });

        tabWidget.getChildAt(4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(myApplication.getCustomerId())) {


                    Intent intent = new Intent(SumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    tabHost.setCurrentTab(4);


                }
            }



        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.personalInfo:
//                Intent intent = new Intent(SumActivity.this,MyAccountActivity.class);
//                startActivity(intent);
//                break;


        }
    }


    public void onBackPressed() {
        //        super.onBackPressed()
        if(tabHost.getCurrentTab()==0){
            super.onBackPressed();

        }else
            tabHost.setCurrentTab(0);
    }
}
