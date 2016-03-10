package com.example.newbishengyuan.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newbishengyuan.R;


/**
 * Created by 何弃疗 on 2015/7/9.
 */
public class BackLayout extends LinearLayout {
    public BackLayout(Context context, AttributeSet attributes) {
        super(context,  attributes);
        LayoutInflater.from(context).inflate(R.layout.back, this);
        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).onBackPressed();
            }
        });

    }
}
