package com.example.newbishengyuan.util;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 何弃疗 on 2015/7/15.
 */
public class HttpUtil {
    public  String TAG = "URL";
    public  String TAG1 = "sid";
    private  String url ="http://120.25.162.238/bsy/index.php/";
    private  String sid;
//    发送网络请求的公共方法
    public  void sendHttpRequest(final HashMap hashMap,final String interfaceUrl, final HttpCallbacklistener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                服务器反回的sessionId
                sid = MyApplication.getInstance().getSid();
                HttpClient httpClient = new DefaultHttpClient();
//                url为服务器基址,interfaceUrl为相对路径
                url +=interfaceUrl;
                HttpPost httpPost = new HttpPost(url);
                if(sid!=null){
                    httpPost.setHeader("Cookie", "PHPSESSID=" + sid);
                    Log.d(TAG1, sid);
                }
//                    构造键值对
                List<NameValuePair> params = new ArrayList<>();
                Iterator it = hashMap.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry entry = (Map.Entry)it.next();
                    params.add(new BasicNameValuePair(entry.getKey().toString(),entry.getValue().toString()));
                }
                UrlEncodedFormEntity entity = null;


                HttpResponse httpResponse = null;
                try {
                    entity = new UrlEncodedFormEntity(params,"utf-8");

                    httpPost.setEntity(entity);

                    Log.d(TAG, url);

                    httpResponse = httpClient.execute(httpPost);


                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity, "utf-8");
                        Log.d(TAG,response);
                        JSONObject jsonObject = null;
                        String code = null;
                        try {
                            jsonObject = new JSONObject(response);
                           code = jsonObject.getString("code");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                   返回结果成功的话回调服务器响应
                        if (listener != null) {

                            listener.onFinish(response);
                        }


                    }
                }
//                网络异常做回调处理
                catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }

            }
        }).start();
    }


}