package com.example.newbishengyuan.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.test.Shop;

import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/23.
 */
public class ShopAdapter extends ArrayAdapter<Shop> {
    private int resourceId;
    public ShopAdapter(Context context, int resource, List<Shop> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.shopImage = (ImageView) view.findViewById(R.id.image);
            viewHolder.shopName = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);

        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.shopImage.setImageResource(shop.getImageId());
        viewHolder.shopName.setText(shop.getName());
        return view;





    }

      class ViewHolder{
        ImageView shopImage;
        TextView shopName;
    }

}
