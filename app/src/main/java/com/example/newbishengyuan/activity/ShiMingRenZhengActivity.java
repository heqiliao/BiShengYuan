package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 何弃疗 on 2015/7/26.
 */
public class ShiMingRenZhengActivity extends BaseActivity {
    private EditText xuehao;
    private EditText shenfenzheng;
    private Button queding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shimingrenzheng);
        xuehao = (EditText) findViewById(R.id.xuehao);
        shenfenzheng = (EditText) findViewById(R.id.shenfenzhenghao);
        queding = (Button) findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hashMap = new HashMap();
                hashMap.put("idcard",xuehao.getText().toString());
                hashMap.put("student_number",shenfenzheng.getText().toString());
                String interfaceUrl = "Index/Customer/certification";
                confirm(hashMap,interfaceUrl);
            }
        });
    }

    private void confirm(HashMap hashMap, String interfaceUrl) {
    new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
        @Override
        public void onFinish(String response) {
            String code = null;
            try {
                JSONObject json = new JSONObject(response);
                code = json.getString("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(code.equals("100000")){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ShiMingRenZhengActivity.this,"实名认证成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        @Override
        public void onError(Exception e) {

        }
    });
    }
}
