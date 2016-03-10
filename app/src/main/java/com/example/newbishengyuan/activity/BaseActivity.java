package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.newbishengyuan.util.ActivityCollector;
import com.example.newbishengyuan.util.MyApplication;

/**
 * Created by 何弃疗 on 2015/7/31.
 */
public class BaseActivity extends Activity {
    boolean isLoginNeeded = false;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
   MyApplication myApplication = (MyApplication) getApplication();
        if(isLoginNeeded){
            if(TextUtils.isEmpty(myApplication.getCustomerId())) {

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
