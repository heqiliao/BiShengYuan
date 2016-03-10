package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.out.GridPasswordView;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 何弃疗 on 2015/8/5.
 */
public class PayActivity extends Activity{
    private Button confirm;
    private GridPasswordView view;
    private  Bundle  bundle = new Bundle();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mima);

        view = (GridPasswordView) findViewById(R.id.gpv_customUi);

        bundle = getIntent().getExtras();
        bundle.putString("trade_pass",view.getPassWord());

        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hashMap = new HashMap();
                hashMap.put("product_id", bundle.getString("product_id"));
                hashMap.put("sum", bundle.getString("sum"));
                hashMap.put("trade_pass",view.getPassWord());
                String interfaceUrl = "Index/Finance/buyProduct";
              pay(hashMap,interfaceUrl);

            }
        });
    }

    private void pay(HashMap hashMap, String interfaceUrl) {


        new HttpUtil().sendHttpRequest(hashMap,interfaceUrl,new HttpCallbacklistener() {
                    @Override
                    public void onFinish(String response) {
                        String code = null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            code = jsonObject.getString("code");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(code.equals("100000")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(PayActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PayActivity.this,JiaoYiActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }


                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }

        );
    }
    }



