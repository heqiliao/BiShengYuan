package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.FinanceProduct;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class XfbOneYearActivity extends BaseActivity{
    private TextView yield;
    private Button touzi;
    private TextView jiamian;
    private TextView goumai;
    private Spinner spinner;
    private String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuefeibaoxiangqing_one);
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(1);
        yield = (TextView) findViewById(R.id.yield);
        touzi = (Button) findViewById(R.id.lijitouzi);
        spinner = (Spinner) findViewById(R.id.spi);
        jiamian = (TextView) findViewById(R.id.jiammian);
        goumai = (TextView) findViewById(R.id.goumai);
        FinanceProduct financeProduct = (FinanceProduct) getIntent().getSerializableExtra("xuefeibao");
        string = financeProduct.getProduct_id();
        String string1 = num.format(Double.parseDouble(financeProduct.getYield()));
        yield.setText(string1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                int i = (Integer.parseInt(result)) * 5;
                String string = String.valueOf(i);
                jiamian.setText(result);
                goumai.setText(string);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        touzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XfbOneYearActivity.this, PayActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("product_id", string);
                bundle.putString("sum", goumai.getText().toString());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


}
