package com.example.newbishengyuan.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class ZhangdanAdapter extends ArrayAdapter<Zhangdan> {
    private int resourceId;


    private   class ViewHolder{
        private TextView dateText;
        private TextView contentText;
        private TextView name;



    }

    public ZhangdanAdapter(Context context, int resource, List<Zhangdan> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zhangdan luntan = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.dateText = (TextView) view.findViewById(R.id.date);
            viewHolder.contentText = (TextView) view.findViewById(R.id.content);
            viewHolder.name = (TextView) view.findViewById(R.id.id1);

            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dateText.setText(luntan.getDate());
        viewHolder.contentText.setText(luntan.getContent());
        viewHolder.name.setText(luntan.getTitle());



        return view;


    }
}
