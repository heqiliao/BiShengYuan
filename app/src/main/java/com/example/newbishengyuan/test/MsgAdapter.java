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
 * Created by 何弃疗 on 2015/7/26.
 */
public class MsgAdapter  extends ArrayAdapter<Msg>{
    private int resourceId;
    public MsgAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    private   class ViewHolder{
        private TextView dateText;
        private TextView contentText;

        private ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.dateText = (TextView) view.findViewById(R.id.riqi);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.zhaopian);
            viewHolder.contentText = (TextView) view.findViewById(R.id.content);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dateText.setText(msg.getDate());
        viewHolder.contentText.setText(msg.getContent());
        ImageLoader.getInstance().displayImage(msg.getImageId(),viewHolder.imageView);



        return view;


    }
}
