package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.util.ActivityCollector;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 何弃疗 on 2015/7/26.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout qingchuhuancun;
    private RelativeLayout xiaoxiguanli;
    private RelativeLayout aboutUs;
    private RelativeLayout banbenjiance;
    private RelativeLayout loginOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        qingchuhuancun = (RelativeLayout) findViewById(R.id.qingchuhuancun);
        xiaoxiguanli = (RelativeLayout) findViewById(R.id.xiaoxiguanli);
        aboutUs = (RelativeLayout) findViewById(R.id.guanyuwomen);
        banbenjiance = (RelativeLayout) findViewById(R.id.banbenjiance);
        loginOut = (RelativeLayout) findViewById(R.id.tuichudenglu);

        qingchuhuancun.setOnClickListener(this);
        xiaoxiguanli.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        banbenjiance.setOnClickListener(this);
        loginOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qingchuhuancun:
                break;
            case R.id.xiaoxiguanli:
                Intent intent = new Intent(this,MyMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.guanyuwomen:
                Intent intent2 = new Intent(this,AboutUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.banbenjiance:
                break;
            case R.id.tuichudenglu:
                HashMap hashMap = new HashMap();

                String interfaceUrl = "Index/Customer/logout";
                MyApplication myApplication = MyApplication.getInstance();

                loginOut(hashMap ,interfaceUrl);
//                ActivityCollector.finishAll();

                break;
        }

    }

    private void loginOut(HashMap hashMap, String interfaceUrl) {



            new HttpUtil().sendHttpRequest(hashMap, interfaceUrl,new HttpCallbacklistener() {
                @Override
                public void onFinish(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        final String message = jsonObject.getString("message");
                        MyApplication.getInstance().setCustomerId(null);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
                               for (Activity activity:ActivityCollector.activities){
                                   Log.d("Activity",activity.getClass().getSimpleName());
                               }
                                ActivityCollector.finishAll();
                                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                                startActivity(intent);



                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SettingActivity.this, "退出异常", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }

}
