package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Blog;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.test.Good;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/28.
 */
public class WoDeDingDanActivity extends BaseActivity {
    private TabHost tabHost;
    private TabWidget tabWidget;
    private List<Order> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dingdantab);
        list = (List<Order>) getIntent().getSerializableExtra("5");
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已完成").setContent(R.id.wanchengFragment));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("未完成").setContent(R.id.weiwanchengFragment));
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
//            //修改Tabhost高度和宽度
//            tabWidget.getChildAt(i).getLayoutParams().height = 30;
//            tabWidget.getChildAt(i).getLayoutParams().width = 65;
            //修改显示字体大小
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(18);
//            tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
        }

    }
}
