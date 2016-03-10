package com.example.newbishengyuan.util;

/**
 * Created by 何弃疗 on 2015/7/15.
 */
public interface HttpCallbacklistener {
    void onFinish(String response);
    void onError(Exception e);
}
