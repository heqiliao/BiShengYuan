package com.example.newbishengyuan.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/28.
 */
public class WeiWanChengFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weiwancheng_fragment,container,false);
        return  view;
    }
}