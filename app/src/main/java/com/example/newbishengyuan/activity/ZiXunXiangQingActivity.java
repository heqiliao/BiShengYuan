package com.example.newbishengyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Date;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class ZiXunXiangQingActivity extends BaseActivity {

    private TextView admin;
    private TextView title;
    private ImageView photo;
    private TextView content;
    private TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixunxiangqing);
        admin = (TextView) findViewById(R.id.admin);
        title = (TextView) findViewById(R.id.title1);
        date = (TextView) findViewById(R.id.date);
        photo = (ImageView) findViewById(R.id.photo);
        content = (TextView) findViewById(R.id.content);

        Intent intent = getIntent();
        admin.setText(intent.getStringExtra("admin"));
        title.setText(intent.getStringExtra("title"));
        date.setText(intent.getStringExtra("date"));
        content.setText(intent.getStringExtra("content"));
        ImageLoader.getInstance().displayImage(intent.getStringExtra("photo"),photo);

    }
}
