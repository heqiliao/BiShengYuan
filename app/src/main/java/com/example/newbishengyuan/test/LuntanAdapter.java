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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class LuntanAdapter extends ArrayAdapter<Luntan>{
    private int resourceId;


    private   class ViewHolder{
        private TextView dateText;
        private TextView contentText;
        private TextView name;
        private ImageView head;
        private ImageView contentPhoto;
        private TextView praise;
        private TextView comment;


    }

    public LuntanAdapter(Context context, int resource, List<Luntan> objects) {
        super(context, resource, objects);
      resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Luntan luntan = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.dateText = (TextView) view.findViewById(R.id.date);
            viewHolder.contentText = (TextView) view.findViewById(R.id.id2);
            viewHolder.name = (TextView) view.findViewById(R.id.id1);
            viewHolder.head = (ImageView) view.findViewById(R.id.photo);
            viewHolder.contentPhoto = (ImageView) view.findViewById(R.id.id3);
            viewHolder.praise = (TextView) view.findViewById(R.id.id5);
            viewHolder.comment = (TextView) view.findViewById(R.id.id4);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dateText.setText(luntan.getTime());
        viewHolder.contentText.setText(luntan.getSummary());
        viewHolder.name.setText(luntan.getName());
        viewHolder.praise.setText(luntan.getPraise_count());
        viewHolder.comment.setText(luntan.getPraise_count());
        ImageLoader.getInstance().displayImage(luntan.getHead(), viewHolder.head);
        ImageLoader.getInstance().displayImage(luntan.getPicture(),viewHolder.contentPhoto);


        return view;


    }
}
