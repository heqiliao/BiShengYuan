package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Bill;
import com.example.newbishengyuan.test.Good;
import com.example.newbishengyuan.test.Zhangdan;
import com.example.newbishengyuan.test.ZhangdanAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class ZhangDanActivity extends BaseActivity {
    private List<Bill> billList = new ArrayList<>();
    private List<Zhangdan> zhangdanList = new ArrayList<>();
  private ListView listView;
    private TextView content;
    private SimpleDateFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhangdan);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        listView = (ListView) findViewById(R.id.zhangdanList);
        content = (TextView) findViewById(R.id.content);
        billList = (List<Bill>) getIntent().getSerializableExtra("bill");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        init();
        ZhangdanAdapter zhangdanAdapter = new ZhangdanAdapter(this,R.layout.zhangdan_item,zhangdanList);
        listView.setAdapter(zhangdanAdapter);

    }

    private void init() {
        for(Bill bill:billList){
            Zhangdan zhangdan = new Zhangdan(bill.getStatus(),format.format(Integer.parseInt(bill.getTime())),bill.getDescription());
            zhangdanList.add(zhangdan);
        }

    }
}
