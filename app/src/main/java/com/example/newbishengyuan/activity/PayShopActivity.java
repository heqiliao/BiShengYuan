package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.out.GridPasswordView;
import com.example.newbishengyuan.test.Good;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class PayShopActivity extends Activity{
    private Button confirm;
    private GridPasswordView view;
    private  String str;
    private List<Order> list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mima);

        view = (GridPasswordView) findViewById(R.id.gpv_customUi);

        Intent intent = getIntent();
        list = (List<Order>) intent.getSerializableExtra("order");
//        str = intent.getStringExtra("order_id");

        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                JSONArray jsonArray = new JSONArray();
//                for(Order order:list){
//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("order_id",order.getOrder_id());
//
//                        jsonArray.put(jsonObject);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }

                String[] a = new String[list.size()];
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("[");
               for(int i=0;i<list.size();i++){
                   stringBuilder.append("\"");
                 stringBuilder.append(list.get(i).getOrder_id());
                   stringBuilder.append("\"");
                   if(i<list.size()-1)
                   stringBuilder.append(",");
               }
                stringBuilder.append("]");

                String string = stringBuilder.toString();
                HashMap hashMap = new HashMap();
                hashMap.put("order_id_list", string);
                hashMap.put("payment", "0");
                hashMap.put("trade_pass",view.getPassWord());
                String interfaceUrl = "Index/Order/payForOrder";
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
                                    Toast.makeText(PayShopActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PayShopActivity.this,JiaoYiActivity.class);
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
