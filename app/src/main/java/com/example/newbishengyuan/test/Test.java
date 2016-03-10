package com.example.newbishengyuan.test;

import android.text.StaticLayout;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class Test {

    public static  void main(String[] args){
        Test test = new Test();
    }

    public Test(){
        String[] a = {"1","2"};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        stringBuilder.append(a);
        stringBuilder.append("\"");

        System.out.print(stringBuilder.toString());
    }
}
