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
 * Created by 何弃疗 on 2015/7/27.
 */
public class ZhiFuRenZhengActivity extends BaseActivity{
    private com.example.newbishengyuan.out.GridPasswordView gpv_customUi;
    private com.example.newbishengyuan.out.GridPasswordView gpv_customUi1;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mimarenzheng);
        gpv_customUi = (com.example.newbishengyuan.out.GridPasswordView) findViewById(R.id.gpv_customUi);
        gpv_customUi1 = (com.example.newbishengyuan.out.GridPasswordView) findViewById(R.id.gpv_customUi1);
        button = (Button) findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String string = gpv_customUi.getPassWord();
            String string1 =gpv_customUi1.getPassWord();
                HashMap hashMap = new HashMap();
                hashMap.put("trade_pass", string);

                String interfaceUrl = "Index/AccountSecurity/setTradePwd";
                confirm(hashMap, interfaceUrl);
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
                if (code.equals("100000")) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhiFuRenZhengActivity.this, "设置支付密码成功", Toast.LENGTH_SHORT).show();
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
