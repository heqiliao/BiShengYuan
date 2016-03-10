package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Address;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


/**
 * Created by 何弃疗 on 2015/7/25.
 */
public class MyAccountActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout zhanghaoanquan;
    private RelativeLayout nichengRe;
    private RelativeLayout yuanxiRe;
    private RelativeLayout xingbieRe;
    private RelativeLayout addressRe;
    private TextView nicheng;
    private TextView xingming;
    private TextView xingbie;
    private TextView yuanxi;
    private TextView shoujihao ;
    private TextView zhengjianhao;
    private TextView xuehao;

    private static String TAG = "ADDRESS";
    private static String TAG1 = "PAY";
    private PtrClassicFrameLayout inprogressPtr;
    private MyApplication myApplication;

//    @Override
//    protected void onResume() {
//        super.onResume();
//        nicheng.setText(myApplication.getName());
//        shoujihao.setText(myApplication.getPhoneNum());
//        shouhuodizhi.setText(myApplication.getDetails());
//        xingbie.setText(myApplication.getSex());
//        yuanxi.setText(myApplication.getDepartment());
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        myApplication = (MyApplication) getApplication();
        init();
        zhanghaoanquan = (RelativeLayout) findViewById(R.id.zhanghaoanquan);
        nichengRe = (RelativeLayout) findViewById(R.id.nichengRe);
        xingbieRe = (RelativeLayout) findViewById(R.id.sexRe);
        yuanxiRe = (RelativeLayout)findViewById(R.id.yuanxiRe);
        addressRe = (RelativeLayout) findViewById(R.id.address);
        inprogressPtr = (PtrClassicFrameLayout) findViewById(R.id.inprogress_ptr);
        nicheng = (TextView) findViewById(R.id.nicheng);
        shoujihao = (TextView) findViewById(R.id.shoujihao);

        inprogressPtr = (PtrClassicFrameLayout) findViewById(R.id.inprogress_ptr);


        inprogressPtr.setPtrHandler(new PtrHandler() {


            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
//                ptrFrameLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ptrFrameLayout.refreshComplete();
//                    }
//                }, 1800);
                nicheng.setText(myApplication.getName());
                shoujihao.setText(myApplication.getPhoneNum());

                xingbie.setText(myApplication.getSex());
                yuanxi.setText(myApplication.getDepartment());
                ptrFrameLayout.refreshComplete();
            }
        });
        zhanghaoanquan.setOnClickListener(this);
        nichengRe.setOnClickListener(this);
        xingbieRe.setOnClickListener(this);
        yuanxiRe.setOnClickListener(this);
        addressRe.setOnClickListener(this);
    }

    private void init() {
        nicheng = (TextView) findViewById(R.id.nicheng);
        xingming = (TextView) findViewById(R.id.xinming);
        xingbie = (TextView) findViewById(R.id.xinbie);
        yuanxi = (TextView) findViewById(R.id.yuanxi);
        shoujihao = (TextView) findViewById(R.id.shoujihao);
        zhengjianhao = (TextView) findViewById(R.id.zhengjianhao);
        xuehao = (TextView) findViewById(R.id.xuehao);


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        nicheng.setText(bundle.getString("name"));
        xingming.setText(bundle.getString("realname"));
        xingbie.setText(bundle.getString("sex"));
        yuanxi.setText(bundle.getString("department"));
        shoujihao.setText(bundle.getString("phonenum"));
        zhengjianhao.setText(bundle.getString("idcard"));
        xuehao.setText(bundle.getString("student_number"));




    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhanghaoanquan:
                Intent intent = new Intent(MyAccountActivity.this,ZhangHuAnQuanActivity.class);
                startActivity(intent);
                break;
            case R.id.nichengRe:
                Intent intent1 = new Intent(MyAccountActivity.this,NiChengActivity.class);
                startActivity(intent1);
                break;
            case R.id.sexRe:
                Intent intent2 = new Intent(MyAccountActivity.this,SexActivity.class);
                startActivity(intent2);
                break;
            case R.id.yuanxiRe:
                Intent intent3 = new Intent(MyAccountActivity.this,YuanXiActivity.class);
                startActivity(intent3);
                break;
            case R.id.address:
                HashMap hashMap = new HashMap();
                String interfaceUrl = "Index/Address/getAddress";
                queryAddress(hashMap,interfaceUrl);
                break;


        }
    }

    private void queryAddress(HashMap hashMap, String interfaceUrl) {

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

                    String code = null;
                    try {
                        code = new JSONObject(response).getString("code");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                if(code.equals("100000")) {

                    final Address address = (Address) JsonDecoder.decode(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MyAccountActivity.this, AddressActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("type", address.getType());
                            bundle.putString("name", address.getName());
                            bundle.putString("phonenum", address.getPhonenum());
                            bundle.putString("postcode", address.getPostcode());
                            bundle.putString("area", address.getArea());
                            bundle.putString("details", address.getDetails());
                            bundle.putString(TAG,"0");
                            bundle.putString(TAG1,"");

                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }else {
                    Intent intent = new Intent(MyAccountActivity.this, AddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(TAG,"1");
                    bundle.putString(TAG1,"");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }





            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyAccountActivity.this, "收货地址异常", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }



}
