package com.example.newbishengyuan.util;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/16.
 */
public class MyApplication extends Application {
    private String customerId;

    private String name;
    private String phoneNum;
    private String details;
    private String sex;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    private static MyApplication instance;


    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }



    private static Context context;


//    任何地方获取Context
    public static Context getContext() {
        return context;
    }

//    任何地方获取Application
    public static MyApplication getInstance() {

        return instance;

    }


    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).
                defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .threadPoolSize(4)
                .build();
        ImageLoader.getInstance().init(config);
        instance = this;
        context = getApplicationContext();
    }
}
