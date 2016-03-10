package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Customer;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.ActivityCollector;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by 何弃疗 on 2015/7/15.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText name;
    private EditText passWord;
    private Button login;
    private MyApplication app;
    private TextView register;
    private TextView forget;
    private CheckBox checkBox;
    private ImageView back;
    private MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        myApplication = (MyApplication) getApplication();
        app = (MyApplication) getApplication();
        name = (EditText)findViewById(R.id.name);
        passWord = (EditText)findViewById(R.id.passWord);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.register);
        forget = (TextView) findViewById(R.id.forgetPassword);
        checkBox = (CheckBox) findViewById(R.id.check);
        back = (ImageView) findViewById(R.id.back);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        name.setOnClickListener(this);
        passWord.setOnClickListener(this);
        forget.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;

//            登陆按钮做出的响应
            case R.id.login:


                HashMap hashMap = new HashMap();
                hashMap.put("username",name.getText().toString());
                hashMap.put("password", passWord.getText().toString());
                String interfaceUrl = "Index/Index/login";
//                向服务器发送登录请求
                login(hashMap,interfaceUrl);
                break;

            case R.id.forgetPassword:
                Intent intent2 = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent2);
                break;

            case R.id.back:
                onBackPressed();
                break;


        }

    }
//             登录按钮发送网络请求
    private void login(HashMap hashMap,String string) {
      new HttpUtil().sendHttpRequest(hashMap, string,new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
//                解析服务器返回数据
                Customer customer = (Customer) JsonDecoder.decode(response);
                    String customer_id = customer.getCustomer_id();
                    String status = customer.getStatus();
                    String picture = customer.getPicture();
                    String name = customer.getName();
                    String balance = customer.getBalance();
                    String sid = customer.getSid();
                myApplication.setCustomerId(customer_id);
                myApplication.setName(name);
                myApplication.setSid(sid);
                runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            提示登录成功
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, SumActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            }
            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        提示登录异常
                        Toast.makeText(LoginActivity.this, "登录异常", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
      Intent intent = new Intent(this,SumActivity.class);
        startActivity(intent);
        finish();
    }
}
