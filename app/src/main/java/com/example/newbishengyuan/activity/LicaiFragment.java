package com.example.newbishengyuan.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Bill;
import com.example.newbishengyuan.model.Customer;
import com.example.newbishengyuan.model.FinanceProduct;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/24.
 */
public class LicaiFragment extends Fragment implements View.OnClickListener{
    private TextView zhangdan;
    private RelativeLayout xuefeibao;
    private RelativeLayout zhuxuebao;
    private TextView shouyi;
    private TextView jine;
    private String balance;
    private String yesterday_income;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//     needLogin();
        View view = inflater.inflate(R.layout.licai_fragment,container,false);
        zhangdan = (TextView) view.findViewById(R.id.zhangdan);
        xuefeibao = (RelativeLayout) view.findViewById(R.id.xuefeibao);
        zhuxuebao = (RelativeLayout) view.findViewById(R.id.zhuxuebao);
        shouyi = (TextView) view.findViewById(R.id.shouyi);
        jine = (TextView) view.findViewById(R.id.jine);
        zhangdan.setOnClickListener(this);
        xuefeibao.setOnClickListener(this);
        zhuxuebao.setOnClickListener(this);

        queryPersonalCenter();
        return view;
    }


    private void queryPersonalCenter() {

        HashMap hashMap = new HashMap();
        String interfaceUrl = "Index/PersonalCenter/index";
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONObject financeJSON = result.getJSONObject("MyFinance");
                    JSONObject billJSON = result.getJSONObject("MyBill");
                    balance = financeJSON.getString("balance");
                    yesterday_income = financeJSON.getString("yesterday_income");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        shouyi.setText(yesterday_income);
                        jine.setText(balance);

//                        Toast.makeText(SumActivity.this, "个人中心页面成功", Toast.LENGTH_SHORT).show();


                    }
                });


            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "个人中心异常", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhangdan:
                HashMap hashMap2 = new HashMap();
                hashMap2.put("page","0");
                hashMap2.put("num", "10");
                String interfaceUrl2 = "Index/Finance/getHistory";
                toZhangdan(hashMap2, interfaceUrl2);

                break;
            case R.id.xuefeibao:
//                Intent intent1 = new Intent(getActivity(),XueFeiBaoActivity.class);
//                startActivity(intent1);
                HashMap hashMap = new HashMap();
                hashMap.put("kind_id", "3");
                String interfaceUrl = "Index/Finance/getProductList";
                xueFeiBao(hashMap,interfaceUrl);
                break;
            case R.id.zhuxuebao:
//                Intent intent2 = new Intent(getActivity(),ZhuXueBaoActivity.class);
//                startActivity(intent2);
                HashMap hashMap1 = new HashMap();
                hashMap1.put("kind_id", "2");
                String interfaceUrl1 = "Index/Finance/getProductList";
                zhuxueBao(hashMap1, interfaceUrl1);
                break;
        }

    }

    private void toZhangdan(HashMap hashMap1, String interfaceUrl1) {
        new HttpUtil().sendHttpRequest(hashMap1, interfaceUrl1, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                List<Bill> bill = new ArrayList<>();
             bill = (List<Bill>) JsonDecoder.decode(response);
                Intent intent = new Intent(getActivity(),ZhangDanActivity.class);
                intent.putExtra("bill",(Serializable)bill);
                startActivity(intent);

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void zhuxueBao(HashMap hashMap, String interfaceUrl) {

        new HttpUtil().sendHttpRequest(hashMap,interfaceUrl,new HttpCallbacklistener() {
                    @Override
                    public void onFinish(String response) {




                        List<FinanceProduct> list = (List<FinanceProduct>) JsonDecoder.decode(response);
                        Intent intent = new Intent(getActivity(),ZhuXueBaoActivity.class);
                        intent.putExtra("zhuxuebao",(Serializable)list);
                        startActivity(intent);


                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }

        );
    }

    private void xueFeiBao(HashMap hashMap,String interfaceUrl) {

            new HttpUtil().sendHttpRequest(hashMap,interfaceUrl,new HttpCallbacklistener() {
                        @Override
                        public void onFinish(String response) {


                            List<FinanceProduct> list = (List<FinanceProduct>) JsonDecoder.decode(response);
                            Intent intent = new Intent(getActivity(),XueFeiBaoActivity.class);
                            intent.putExtra("xuefeibao",(Serializable)list);
                            startActivity(intent);


                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    }

                );



    }
}



