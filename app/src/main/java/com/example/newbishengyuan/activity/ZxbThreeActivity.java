package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.FinanceProduct;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class ZxbThreeActivity extends BaseActivity {
    private TextView amount;
    private TextView yield;
    private Button touzi;
    private String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuxuebaoxiangqing_three);
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(1);
        yield = (TextView) findViewById(R.id.yield);
        amount = (TextView) findViewById(R.id.touziAmount);
        touzi = (Button) findViewById(R.id.lijitouzi);
        FinanceProduct financeProduct = (FinanceProduct) getIntent().getSerializableExtra("zhuxuebao");
        string = financeProduct.getProduct_id();

        touzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZxbThreeActivity.this, PayActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("product_id", string);
                bundle.putString("sum", amount.getText().toString());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
