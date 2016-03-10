package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/3.
 */
public class AddressActivity extends Activity {
    private EditText shouhuoren;
    private EditText shoujihao;
    private EditText suozaidiqu;
    private EditText xiangxidizhi;
    private EditText youzhengbianma;
    private String type =  "1";
    private String pay;
    private Button save;
    private MyApplication myApplication;
    private  String string;
    private  String string1;
    private  String string2;
    private  String string3;
    private  String string4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addres);
        myApplication = (MyApplication) getApplication();
        init();
        save = (Button) findViewById(R.id.save);
        shouhuoren = (EditText) findViewById(R.id.shouhuoren);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
        suozaidiqu = (EditText) findViewById(R.id.suozaidiqu);
        xiangxidizhi = (EditText) findViewById(R.id.xiangxidizhi);
        youzhengbianma = (EditText) findViewById(R.id.youzhengbianma);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(pay)) {
                    string = shouhuoren.getText().toString();
                    string1 = shoujihao.getText().toString();
                    string2 = suozaidiqu.getText().toString();
                    string3 = xiangxidizhi.getText().toString();
                    string4 = youzhengbianma.getText().toString();
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", type);
                    hashMap.put("name", string);
                    hashMap.put("phonenum", string1);
                    hashMap.put("postcode", string4);
                    hashMap.put("area", string2);
                    hashMap.put("details", string3);
                    String interfaceUrl = "Index/Address/modifyAddress";
                    hashMap.put("name", string);
                    changeAddress(hashMap, interfaceUrl);
                } else {
                    string = shouhuoren.getText().toString();
                    string1 = shoujihao.getText().toString();
                    string2 = suozaidiqu.getText().toString();
                    string3 = xiangxidizhi.getText().toString();
                    string4 = youzhengbianma.getText().toString();
                    HashMap hashMap = new HashMap();
                    hashMap.put("product_list", pay);
                    hashMap.put("name", string);
                    hashMap.put("phonenum", string1);
                    hashMap.put("postcode", string4);
                    hashMap.put("area", string2);
                    hashMap.put("details", string3);
                    String interfaceUrl = "Index/Order/submitOrderForward";
                    hashMap.put("remarks", "");
                    payAddress(hashMap, interfaceUrl);
                }
            }
        });


    }

    private void payAddress(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                List<Order> list = (List<Order>) JsonDecoder.decode(response);
                Intent intent = new Intent(AddressActivity.this,PayShopActivity.class);
//                intent.putExtra("order_id",list.get(0).getOrder_id());
                intent.putExtra("order", (Serializable) list);

                startActivity(intent);

            }
            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void changeAddress(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
               String code =null;
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
                            myApplication.setName(string);
                            myApplication.setPhoneNum(string1);
                            myApplication.setDetails(string4);
                            Toast.makeText(AddressActivity.this,"更改收货地址成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddressActivity.this,"更改收货地址失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void init() {
        shouhuoren = (EditText) findViewById(R.id.shouhuoren);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
        suozaidiqu = (EditText) findViewById(R.id.suozaidiqu);
        xiangxidizhi = (EditText) findViewById(R.id.xiangxidizhi);
        youzhengbianma = (EditText) findViewById(R.id.youzhengbianma);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        if(bundle.getString("ADDRESS").equals("0")) {
            shouhuoren.setText(bundle.getString("name"));
            shoujihao.setText(bundle.getString("phonenum"));
            suozaidiqu.setText(bundle.getString("area"));
            xiangxidizhi.setText(bundle.getString("details"));
            youzhengbianma.setText(bundle.getString("postcode"));
            type = bundle.getString("type");
            pay = bundle.getString("PAY");
        }
        else {
            pay = bundle.getString("PAY");
        }

    }
}
