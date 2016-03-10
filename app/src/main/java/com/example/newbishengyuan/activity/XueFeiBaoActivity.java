package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.FinanceProduct;
import com.example.newbishengyuan.test.Good;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class XueFeiBaoActivity extends BaseActivity implements View.OnClickListener{
    private TextView shuoming;
    private TextView touzi1;
    private TextView touzi2;
    private TextView touzi3;
   private TextView shouyili1;
    private TextView shouyili2;
    private TextView shouyili3;
    private String description;
    private List<FinanceProduct> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuefeibao);
        shuoming = (TextView) findViewById(R.id.shuoming);
        touzi1 = (TextView) findViewById(R.id.lijitouzi1);
        touzi2 = (TextView) findViewById(R.id.lijitouzi2);
        touzi3 = (TextView) findViewById(R.id.lijitouzi3);
        shouyili1 = (TextView) findViewById(R.id.shouyili1);
        shouyili2 = (TextView) findViewById(R.id.shouyili2);
        shouyili3 = (TextView) findViewById(R.id.shouyili3);

        init();
        shuoming.setOnClickListener(this);
        touzi1.setOnClickListener(this);
        touzi2.setOnClickListener(this);
        touzi3.setOnClickListener(this);



    }

    private void init() {
        list = (List<FinanceProduct>) getIntent().getSerializableExtra("xuefeibao");
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(1);

        description = list.get(0).getPattern();
        String string = num.format(Double.parseDouble(list.get(0).getYield()));
        String string1 = num.format(Double.parseDouble(list.get(1).getYield()));
        String string2 = num.format(Double.parseDouble(list.get(2).getYield()));
        shouyili1.setText(string);
        shouyili2.setText(string1);
        shouyili3.setText(string2);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shuoming:
                Intent intent = new Intent(this,XfbShuoMingActivity.class);
                intent.putExtra("shuoming",description);
                startActivity(intent);
                break;
            case R.id.lijitouzi1:
                Intent intent1 = new Intent(this,XfbOneYearActivity.class);
                intent1.putExtra("xuefeibao",list.get(0));
                startActivity(intent1);
                break;
            case R.id.lijitouzi2:
                Intent intent2 = new Intent(this,XfbTwoYearActivity.class);
                intent2.putExtra("xuefeibao",list.get(1));
                startActivity(intent2);
                break;
            case R.id.lijitouzi3:
                Intent intent3 = new Intent(this,XfbThreeYearActivity.class);
                intent3.putExtra("xuefeibao",list.get(2));
                startActivity(intent3);
                break;
        }

    }
}
