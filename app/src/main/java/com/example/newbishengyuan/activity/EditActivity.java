package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class EditActivity extends BaseActivity {
    private TextView title;
    private EditText huati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabu);
        title = (TextView) findViewById(R.id.title);
        huati = (EditText) findViewById(R.id.huati);
//        title.setTextColor(getResources().getColor(R.color.titleColor));
        Log.d("rr", Color.red(title.getTextColors().getDefaultColor())+"");
        Log.d("gg", Color.green(title.getTextColors().getDefaultColor())+"");
        Log.d("bb", Color.blue(title.getTextColors().getDefaultColor())+"");
//        Log.d("ab", Color.parseColor("#00CCFF")+"");
    }


}
