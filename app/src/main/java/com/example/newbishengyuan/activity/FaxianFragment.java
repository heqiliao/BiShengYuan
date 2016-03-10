package com.example.newbishengyuan.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.newbishengyuan.R;

/**
 * Created by 何弃疗 on 2015/7/24.
 */
public class FaxianFragment extends Fragment implements OnClickListener{
    private ImageView edit;
    private ImageView comment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.loginNeeded();
        View view = inflater.inflate(R.layout.faxian_fragment,container,false);
        edit = (ImageView) view.findViewById(R.id.edit);
        comment = (ImageView) view.findViewById(R.id.id6);
        comment.setOnClickListener(this);
        edit.setOnClickListener(this);
        return view;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                Intent intent = new Intent(getActivity(),EditActivity.class);
                startActivity(intent);
                break;
            case R.id.id6:
                Intent intent1 = new Intent(getActivity(),HuaTiXiangQingActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
