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
public class DengLuMiMaActivity extends BaseActivity {
    private EditText password1;
    private EditText password2;
    private EditText password3;
    private Button queding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglumima);
        password1 = (EditText) findViewById(R.id.passwordBefore);
        password2 = (EditText) findViewById(R.id.password);
        password3 = (EditText) findViewById(R.id.passwordAgain);
        queding = (Button) findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hashMap = new HashMap();
                hashMap.put("old_password",password1.getText().toString());
                hashMap.put("new_password",password2.getText().toString());
                String interfaceUrl = "Index/AccountSecurity/modifyLoginPwd";
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
                if (code.equals("100000")) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DengLuMiMaActivity.this, "更改密码成功", Toast.LENGTH_SHORT).show();
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
