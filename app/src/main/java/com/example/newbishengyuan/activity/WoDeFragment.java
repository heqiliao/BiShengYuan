package com.example.newbishengyuan.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Blog;
import com.example.newbishengyuan.model.Customer;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by 何弃疗 on 2015/7/23.
 */
public class WoDeFragment extends Fragment implements View.OnClickListener{
    public static String TAG = "QUERY";
    private ImageView setting;
    private RelativeLayout wodelicai;
    private RelativeLayout woyaotixian;
    private RelativeLayout wodedingdan;
    private RelativeLayout personalInfo;
    private MyApplication myApplication = MyApplication.getInstance();
    private TextView yonghuming;
    private TextView bishengtongyue;
    private TextView zuorishouyi;
    private TextView yitouzijine;
    private TextView daifukuan;
    private TextView daishouhuo;
    private TextView daipingjia;
    private PtrClassicFrameLayout inprogressPtr;

    private String balance;
    private String topay;
    private String yesterday_income;
    private String  finance_sum;
    private String toreceive;
    private String tocomment;
    private boolean isRefresh;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.loginNeeded();
        View view = inflater.inflate(R.layout.wode_fragment,container,false);
        setting = (ImageView) view.findViewById(R.id.setting);
        wodelicai = (RelativeLayout) view.findViewById(R.id.wodelicai);
        woyaotixian = (RelativeLayout) view.findViewById(R.id.woyaotixian);
        wodedingdan = (RelativeLayout) view.findViewById(R.id.wodedingdan);
        personalInfo = (RelativeLayout) view.findViewById(R.id.personalInfo);
        yonghuming = (TextView) view.findViewById(R.id.yonghuming);
        bishengtongyue = (TextView) view.findViewById(R.id.bishengtongyue);
        zuorishouyi = (TextView) view.findViewById(R.id.zuorishouyi);
        yitouzijine = (TextView) view.findViewById(R.id.yitouzijine);
        daifukuan = (TextView) view.findViewById(R.id.daifukuan);
        daishouhuo = (TextView) view.findViewById(R.id.daishouhuo);
        daipingjia = (TextView) view.findViewById(R.id.daipingjia);
        inprogressPtr = (PtrClassicFrameLayout) view.findViewById(R.id.inprogress_ptr);



        woyaotixian.setOnClickListener(this);
        wodedingdan.setOnClickListener(this);
        wodelicai.setOnClickListener(this);
        setting.setOnClickListener(this);
        personalInfo.setOnClickListener(this);

        inprogressPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                isRefresh = true;
                queryPersonalCenter();

            }
        });

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
                    finance_sum = financeJSON.getString("finance_sum");
                    topay = billJSON.getString("topay");
                    toreceive = billJSON.getString("toreceive");
                    tocomment = billJSON.getString("tocomment");

                } catch (JSONException e) {
                    e.printStackTrace();
                }



             getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        bishengtongyue.setText(balance);
                        zuorishouyi.setText(yesterday_income);
                        yitouzijine.setText(finance_sum);
                        daifukuan.setText(topay);
                        daishouhuo.setText(toreceive);
                        daipingjia.setText(tocomment);

                        yonghuming.setText(myApplication.getName());
                        if(isRefresh){
                            inprogressPtr.refreshComplete();
                            isRefresh = false;

                        }
//                        shouyi.setText(yesterday_income);
//                        jine.setText(balance);

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
            case R.id.setting:
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.wodelicai:
                Intent intent1 = new Intent(getActivity(),WoDeLiCaiActivity.class);
                startActivity(intent1);
                break;
            case R.id.woyaotixian:
                Intent intent2 = new Intent(getActivity(),WoYaoTiXianActivity.class);
                startActivity(intent2);
                break;
            case R.id.wodedingdan:
               HashMap dingdanhashMap = new HashMap();
                dingdanhashMap.put("status","1");
                dingdanhashMap.put("page","0");
                dingdanhashMap.put("num","10");
                String  dingdanInterfaceUrl = "Index/Order/getOrderList";
                queryDingdan(dingdanhashMap,dingdanInterfaceUrl);



                break;
            case R.id.personalInfo:
                HashMap hashMap = new HashMap();
//                hashMap.put("","");
                String interfaceUrl = "Index/Customer/getInfo";
                queryPersonaInfo(hashMap,interfaceUrl);
                break;
            
        }
    }

    private void queryDingdan(HashMap dingdanhashMap, String dingdanInterfaceUrl) {
        new HttpUtil().sendHttpRequest(dingdanhashMap, dingdanInterfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                List<Order> list = (List<Order>) JsonDecoder.decode(response);

                Intent intent = new Intent(getActivity(),WoDeDingDanActivity.class);
                intent.putExtra("5",(Serializable)list);
                startActivity(intent);

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void queryPersonaInfo(HashMap hashMap, String interfaceUrl) {

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl,new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                String code = null;
                try {
               code = new JSONObject(response).getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(code.equals("100000")) {
                    Customer customer = (Customer) JsonDecoder.decode(response);
                    String customer_id = customer.getCustomer_id();
                    final String username = customer.getUsername();
                    final String realname = customer.getRealname();

                    final String sex = customer.getSex();
                    final String phonenum = customer.getPhonenum();
                    String email = customer.getEmail();
                    final String idcard = customer.getIdcard();
                    final String student_number = customer.getStudent_number();
                    final String department = customer.getDepartment();
                    String grade = customer.getGrade();
                    final String student_class = customer.getStudent_class();
                    String remark = customer.getRemark();
                    String status = customer.getStatus();
                    String picture = customer.getPicture();
                    final String name = customer.getName();
                    String balance = customer.getBalance();
                    String sid = customer.getSid();


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(getActivity(), "查询顾客信息成功", Toast.LENGTH_SHORT).show();

                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            bundle.putString("realname", realname);
                            bundle.putString("sex", sex);
                            bundle.putString("department", department);
                            bundle.putString("phonenum", phonenum);
                            bundle.putString("idcard", idcard);
                            bundle.putString("student_number", student_number);
                            Intent intent = new Intent(getActivity(), MyAccountActivity.class);
                            intent.putExtras(bundle);


                            startActivity(intent);
                        }
                    });
                }
                else{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"请重新登陆",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }



            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "查询顾客信息异常", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }




}
