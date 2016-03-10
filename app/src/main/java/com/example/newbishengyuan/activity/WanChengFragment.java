package com.example.newbishengyuan.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Bill;
import com.example.newbishengyuan.model.Blog;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.test.Dingdan;
import com.example.newbishengyuan.test.DingdanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/28.
 */
public class WanChengFragment extends Fragment {
    List<Order> orderList = new ArrayList<>();
    List<Dingdan> dingdanList = new ArrayList<>();
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wancheng_fragment,container,false);
        orderList = (List<Order>) getActivity().getIntent().getSerializableExtra("5");
        init();

listView = (ListView) view.findViewById(R.id.list);
        DingdanAdapter dingdanAdapter = new DingdanAdapter(getActivity(),R.layout.wancheng_item,dingdanList);
        listView.setAdapter(dingdanAdapter);
        return  view;
    }

    private void init() {

        for (Order order:orderList){
            Dingdan dingdan = new Dingdan(order.getNumber(),order.getAmount(),order.getOrder_time(),order.getStatus(),order.getAddress(),order.getDelivery_time());
            dingdanList.add(dingdan);
        }
    }
}
