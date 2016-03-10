package com.example.newbishengyuan.activity;


import android.app.Fragment;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Classification;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.model.Product;
import com.example.newbishengyuan.test.Good;

import com.example.newbishengyuan.test.Shop;
import com.example.newbishengyuan.test.ShopAdapter;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by 何弃疗 on 2015/7/23.
 */
public class ShangChengFragment extends Fragment implements View.OnClickListener{

    private ImageView sousuo1;
    private TextView shuliang;
    private ListView listView;
    private ListView listView1;
    private View footer;

    private Button jiesuan;
    private TextView xianjia;
    private TextView yuanjia;
    private int total;
    private int totalBefore;
    private int shuliangzhi;
    private PtrClassicFrameLayout inprogressPtr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    List<Good> list = new ArrayList<>();
    List<Good> list1 = new ArrayList<>();
    List<Good> list2 = new ArrayList<>();
    List<Shop> shopList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    private List<Classification> classificationList = new ArrayList<>();

    @Override


    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


      View view = inflater.inflate(R.layout.shangcheng_fragment,container,false);

        listView = (ListView) view.findViewById(R.id.goodList);
        listView1 = (ListView) view.findViewById(R.id.shopList);
        initListview1();
        footer = getActivity().getLayoutInflater().inflate(R.layout.listview_footer, null);
        inprogressPtr = (PtrClassicFrameLayout) view.findViewById(R.id.inprogress_ptr);
        shuliang = (TextView) view.findViewById(R.id.shuliang);
        sousuo1 = (ImageView) view.findViewById(R.id.find);
        jiesuan = (Button) view.findViewById(R.id.jiesuan);
        xianjia = (TextView) view.findViewById(R.id.xianjia);
        yuanjia = (TextView) view.findViewById(R.id.yuanjia);
//        listView.addFooterView(footer);

        yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        footer.setClickable(false);
        jiesuan.setOnClickListener(this);
        jiesuan.setClickable(false);
        sousuo1.setOnClickListener(this);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classification_id = classificationList.get(position).getClassification_id();


                HashMap hashMap = new HashMap();
                hashMap.put("classification_id", classification_id);
                hashMap.put("page", "0");
                hashMap.put("num", "10");
                String interfaceUrl = "Index/Product/getProductList";
                itemClicked(hashMap, interfaceUrl);
            }
        });


        inprogressPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {

            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                }

        });


        return view;


    }

    private void itemClicked(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

                final List<Product> productList = (List<Product>) JsonDecoder.decode(response);
                if(productList == null){

                    return;
                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        initListview(productList);
//
//
//                    }
//                });
                EventBus.getDefault().post(productList);


            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initListview1() {
        initGo();

        ShopAdapter shopAdapter = new ShopAdapter(MyApplication.getContext(),R.layout.shopping_item,shopList);

        listView1.setAdapter(shopAdapter);
    }


    void initShop(List<String> list) {

        Shop shop1 = new Shop(list.get(0),R.mipmap.quanxiaofengqiang);
        shopList.add(shop1);
        Shop shop2 = new Shop(list.get(1),R.mipmap.xinpinchangxian);
        shopList.add(shop2);
        Shop shop3 = new Shop(list.get(2),R.mipmap.nannvfushi);
        shopList.add(shop3);
        Shop shop4 = new Shop(list.get(3),R.mipmap.nannvxiebao);
        shopList.add(shop4);
        Shop shop5 = new Shop(list.get(4),R.mipmap.diannaoshouji);
        shopList.add(shop5);
        Shop shop6 = new Shop(list.get(5),R.mipmap.xiuxianshipin);
        shopList.add(shop6);
        Shop shop7 = new Shop(list.get(6),R.mipmap.shenghuoyongpin);
        shopList.add(shop7);
        Shop shop8 = new Shop(list.get(7),R.mipmap.hufucaizhuang);
        shopList.add(shop8);
    }



    private void initGo() {
        HashMap hashMap = new HashMap();
        String interfaceUrl = "Index/Product/getClassificationList";
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

              classificationList = (List<Classification>) JsonDecoder.decode(response);

                for (Classification classification : classificationList) {
                    stringList.add(classification.getName());

                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initShop(stringList);
                    }
                });

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


//            private void initShop() {
//
//        Shop shop1 = new Shop("全校疯抢",R.mipmap.quanxiaofengqiang);
//        shopList.add(shop1);
//        Shop shop2 = new Shop("新品抢鲜",R.mipmap.xinpinchangxian);
//        shopList.add(shop2);
//        Shop shop3 = new Shop("男女服饰",R.mipmap.nannvfushi);
//        shopList.add(shop3);
//        Shop shop4 = new Shop("男女鞋包",R.mipmap.nannvxiebao);
//        shopList.add(shop4);
//        Shop shop5 = new Shop("电脑手机",R.mipmap.diannaoshouji);
//        shopList.add(shop5);
//        Shop shop6 = new Shop("休闲食品",R.mipmap.xiuxianshipin);
//        shopList.add(shop6);
//        Shop shop7 = new Shop("生活用品",R.mipmap.shenghuoyongpin);
//        shopList.add(shop7);
//        Shop shop8 = new Shop("护肤彩妆",R.mipmap.hufucaizhuang);
//        shopList.add(shop8);
//    }

    private void initListview(List<Product> productList) {



list.clear();


        for(Product product:productList){
            Good good = new Good(product.getProduct_id(),product.getName(),product.getMember_price(),product.getPrice(),"0",product.getDefault_url(),product.getSold_amount(),product.getClassification_id());

            list.add(good);
            list2.add(good);
        }


            GoodAdapter goodAdapter = new GoodAdapter(MyApplication.getContext(),R.layout.shopping_item1,list);

            listView.setAdapter(goodAdapter);







        }

    public void onEventMainThread(List<Product> productList){
        list.clear();


        for(Product product:productList){
            Good good = new Good(product.getProduct_id(),product.getName(),product.getMember_price(),product.getPrice(),"0",product.getDefault_url(),product.getSold_amount(),product.getClassification_id());

            list.add(good);
            list2.add(good);
        }


        GoodAdapter goodAdapter = new GoodAdapter(MyApplication.getContext(),R.layout.shopping_item1,list);

        listView.setAdapter(goodAdapter);



    }





    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.find:
//                BarFragment2 barFragment2 = new BarFragment2();
//                FragmentManager fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.main_bar,barFragment2);
//                fragmentTransaction.commit();
//                break;

            case R.id.find:
                Intent intent = new Intent(getActivity(),BarActivity.class);
                startActivity(intent);
                break;


            case R.id.jiesuan:
                quJieSuan();

                break;
        }
    }

    private void quJieSuan() {
//        防止切换回去重复添加之前的元素
        list1.clear();

        for (Good good:list2){
            if(Integer.parseInt(good.getAmount())>0){
                list1.add(good);
            }
        }


        Intent intent = new Intent(getActivity(),CartActivity.class);
        intent.putExtra("good",(Serializable)list1);
        intent.putExtra("money",xianjia.getText().toString());
        startActivity(intent);
    }


    private class GoodAdapter extends ArrayAdapter<Good> {
        private int resourceId;
        private int moneyAccout;

        public GoodAdapter(Context context, int resource, List<Good> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }




        private   class ViewHolder{
            private TextView nameText;
            private TextView priceText;
            private TextView priceBefore;
            private TextView amountText;
            private ImageView imageView;
            private TextView xiaoliangText;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final  Good good = getItem(position);
            View view;
            final ViewHolder viewHolder;
            ImageView increase;
            ImageView decrease;


            if(convertView == null){
                view = LayoutInflater.from(getContext()).inflate(resourceId,null);
                viewHolder = new ViewHolder();
                viewHolder.nameText = (TextView) view.findViewById(R.id.name);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
                viewHolder.priceText = (TextView) view.findViewById(R.id.price);
                viewHolder.priceBefore = (TextView) view.findViewById(R.id.price1);
                viewHolder.priceBefore.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.amountText = (TextView) view.findViewById(R.id.amount);
                viewHolder.xiaoliangText = (TextView) view.findViewById(R.id.xiaoliang);
                view.setTag(viewHolder);




            }else{
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();

            }


            increase = (ImageView) view.findViewById(R.id.increase);
            decrease = (ImageView) view.findViewById(R.id.decrease);
            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jiesuan.setClickable(true);
                    int amount = Integer.parseInt(viewHolder.amountText.getText().toString());
                    jiesuan.setText("去结算");
                    amount += 1;
                    viewHolder.amountText.setText(String.valueOf(amount));
                    good.setAmount(viewHolder.amountText.getText().toString());
                    total += Double.parseDouble(viewHolder.priceText.getText().toString());
                    totalBefore += Double.parseDouble(viewHolder.priceBefore.getText().toString());
                    yuanjia.setText(String.valueOf(totalBefore));
                    yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    xianjia.setText(String.valueOf(total));
                    shuliangzhi += 1;
                    shuliang.setText(String.valueOf(shuliangzhi));
                    shuliang.setVisibility(View.VISIBLE);

//                    if(list1.size()==0){
//                        list1.add(good);
//                    }else{
//                        for(Good good1:list1){
//                            if(good1.getName().equals(good.getName())){
//                                good1.setAmount(String.valueOf(((Integer.parseInt(good1.getAmount()))+1)));
//                            }else{
//                                list1.add(good);
//                            }
//                        }
//
//                    }



                }
            });

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int amount = Integer.parseInt(viewHolder.amountText.getText().toString());
                    if (amount > 0) {


                        amount -= 1;
                        total -= Double.parseDouble(viewHolder.priceText.getText().toString());
                        totalBefore -= Double.parseDouble(viewHolder.priceBefore.getText().toString());
                        yuanjia.setText(String.valueOf(totalBefore));
                        yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        xianjia.setText(String.valueOf(totalBefore));
                        shuliangzhi -= 1;
                        shuliang.setText(String.valueOf(shuliangzhi));

                        viewHolder.amountText.setText(String.valueOf(amount));
                        good.setAmount(viewHolder.amountText.getText().toString());
//                        list1.remove(good);
//                        for(Good good1:list1){
//                            if(good1.getName().equals(good.getName())&&Integer.parseInt(good.getAmount())>0){
//                                list1.remove(good1);
//                                list1.add(good);
//                            }
//                        }

                        if (amount == 0) {
                            jiesuan.setText("请选购");
                            jiesuan.setClickable(false);
                            shuliang.setVisibility(View.INVISIBLE);
                        }

                    }


                }
            });
//       顺序?

            viewHolder.amountText.setText(good.getAmount());

            viewHolder.nameText.setText(good.getName());
            viewHolder.priceText.setText(good.getPrice());
            viewHolder.priceBefore.setText(good.getPrice_before());

//            viewHolder.imageView.setImageResource(good.getImageId());
            viewHolder.xiaoliangText.setText(good.getXiaoliang());
            ImageLoader.getInstance().displayImage(good.getImageId(),
                    viewHolder.imageView);

//            ImageLoader.getInstance().loadImage(good.getImageId(), new SimpleImageLoadingListener() {
//
//                @Override
//                public void onLoadingComplete(String imageUri, View view,
//                                              Bitmap loadedImage) {
//                    super.onLoadingComplete(imageUri, view, loadedImage);
//                    viewHolder.imageView.setImageBitmap(loadedImage);
////
//                }
//
//            });



            return view;


        }
    }

}
