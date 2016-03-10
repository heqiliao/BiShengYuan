package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 何弃疗 on 2015/7/15.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText phoneNum;
    private EditText passWord;
    private EditText passWord1;
    private EditText yanzhengma;
    private Button yanzhengmaButton;
    private Button register;

    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
         myApplication = (MyApplication) getApplication();
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        passWord = (EditText)findViewById(R.id.password);
        passWord1 = (EditText)findViewById(R.id.password_confirm);
        yanzhengma = (EditText)findViewById(R.id.yanzhengma);
        register = (Button)findViewById(R.id.register);
        yanzhengmaButton = (Button)findViewById(R.id.yanzhengmaButton);
       final String string1 = phoneNum.toString();
        final   String string2 = passWord.toString();
        yanzhengmaButton.setOnClickListener(this);
        register.setOnClickListener(this);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap hashMap = new HashMap();
//                hashMap.put("username",string1);
//                hashMap.put("password",string2);
//
//                HttpUtil.sendHttpRequest(hashMap, new HttpCallbacklistener() {
//                    @Override
//                    public void onFinish(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String code = jsonObject.getString("code");
//                            final String message = jsonObject.getString("message");
//                            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
//                            String customerId = jsonObject1.getString("customer_id");
//                            String sid = jsonObject1.getString("sid");
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//
//                                }
//                            });
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(RegisterActivity.this, "注册异常", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yanzhengmaButton:
                HashMap hashMap = new HashMap();
                hashMap.put("phonenum", phoneNum.getText().toString());
                String interfaceUrl = "Index/Register/getCustomerCode";
                register_one(hashMap, interfaceUrl);
                break;
            case R.id.register:


                HashMap hashMap1 = new HashMap();
                hashMap1.put("phonenum",phoneNum.getText().toString());
                hashMap1.put("code", yanzhengma.getText().toString());
                hashMap1.put("password", passWord.getText().toString());
                String interfaceUrl1 = "Index/Register/customer";
                register_two(hashMap1,interfaceUrl1);
                break;

            }

        }

    private void register_two(HashMap hashMap1, String interfaceUrl1) {

       new HttpUtil().sendHttpRequest(hashMap1, interfaceUrl1,new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    final String message = jsonObject.getString("message");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    String sid = jsonObject1.getString("sid");
                    String customer_id = jsonObject1.getString("customer_id");
                    myApplication.setSid(sid);
                    myApplication.setCustomerId(customer_id);
//                          Object o =  JsonDecoder.decode(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
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
                        Toast.makeText(RegisterActivity.this, "注册异常", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void register_one(HashMap hashMap, String interfaceUrl) {

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl,new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    final String message = jsonObject.getString("message");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                  final  String code1 = jsonObject1.getString("code");
                    String sid = jsonObject1.getString("sid");
                myApplication.setSid(sid);



//                          Object o =  JsonDecoder.decode(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            yanzhengma.setText(code1);
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(RegisterActivity.this, "获取验证码异常", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}

