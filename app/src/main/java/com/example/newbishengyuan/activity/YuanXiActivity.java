package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.util.ActivityCollector;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.newbishengyuan.R.id.change;

/**
 * Created by 何弃疗 on 2015/8/3.
 */
public class YuanXiActivity extends Activity {
    private EditText yuanxi;
    private Button save;
    private MyApplication myApplication = MyApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuanxi);
        yuanxi = (EditText) findViewById(change);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hashMap = new HashMap();
                hashMap.put("department", yuanxi.getText().toString());
                String interfaceUrl = "Index/Customer/editInfo";
                save(hashMap, interfaceUrl);

            }
        });
    }

    private void save(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                String code = null;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    code = jsonObject.getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (code.equals("100000")) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myApplication.setDepartment(yuanxi.getText().toString());

                            Toast.makeText(YuanXiActivity.this, "更改院系成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YuanXiActivity.this, "更改院系失败", Toast.LENGTH_SHORT).show();
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
