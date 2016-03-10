package com.example.newbishengyuan.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.test.Dingdan;

import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class DingdanAdapter extends ArrayAdapter<Dingdan> {
    private int resourceId;


    private   class ViewHolder{
        private TextView number;
        private TextView amount;
        private TextView  order_time;
        private TextView  status;
        private TextView  address;
        private TextView  delivery_time;


    }

    public DingdanAdapter(Context context, int resource, List<Dingdan> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dingdan luntan = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.number = (TextView) view.findViewById(R.id.number);
            viewHolder.amount = (TextView) view.findViewById(R.id.amount);
            viewHolder.order_time = (TextView) view.findViewById(R.id.order_time);
            viewHolder.status = (TextView) view.findViewById(R.id.status);
            viewHolder.address = (TextView) view.findViewById(R.id.address);
            viewHolder.delivery_time = (TextView) view.findViewById(R.id.delivery_time);

            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.number.setText(luntan.getNumber());
        viewHolder.amount.setText(luntan.getAmount());
        viewHolder.order_time.setText(luntan.getOrder_time());
        viewHolder.status.setText(luntan.getStatus());
        viewHolder.address.setText(luntan.getAddress());
        viewHolder.delivery_time.setText(luntan.getDelivery_time());

        return view;


    }
}
