package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class WonderfulActivity extends BaseActivity implements View.OnClickListener{
    private ImageView edit;
    private ImageView comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wonderfulhuodong);
        edit = (ImageView) findViewById(R.id.edit);
        comment = (ImageView) findViewById(R.id.id6);
        comment.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                Intent intent = new Intent(this,EditWonderfulActivity.class);
                startActivity(intent);
                break;

                case R.id.id6:
                    Intent intent1 = new Intent(this, HuaTiXiangQingActivity.class);
                    startActivity(intent1);
                    break;

        }
    }
}
