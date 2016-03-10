package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Address;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.test.Good;

import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;
import com.google.gson.JsonIOException;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/4.
 */
public class CartActivity extends BaseActivity implements View.OnClickListener{
    private Button jiesuan;
    private TextView total;
    private List<Good> list = new ArrayList<>();
    private static String TAG = "ADDRESS";
    private static String TAG1 = "PAY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcart);

        jiesuan = (Button) findViewById(R.id.jiesuan);
        total = (TextView) findViewById(R.id.total);

        init();
        jiesuan.setOnClickListener(this);


    }

    private void init() {
        list = (List<Good>) getIntent().getSerializableExtra("good");
        String money = getIntent().getStringExtra("money");
        total.setText(money);


        GoodAdapter goodAdapter = new GoodAdapter(MyApplication.getContext(),R.layout.shopping_item1,list);
        ListView listView = (ListView) findViewById(R.id.cartList);
        listView.setAdapter(goodAdapter);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jiesuan:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                stringBuilder.append("{");
                stringBuilder.append("\"product_id\"");
                JSONArray jsonArray = new JSONArray();
                for(Good good:list){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("product_id",good.getProduct_id());
                        jsonObject.put("quantity",good.getAmount());
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String string = jsonArray.toString();
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("product_id","1");
//                    jsonObject.put("product_i","1");
//                    jsonArray.put(jsonObject);
//                    String s = jsonArray.toString();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

//                for(Good good:list){
//
//String s = {"product_id":good.getProduct_id(),"quantity":good.getAmount()};
//                }





                HashMap hashMap = new HashMap();
                String interfaceUrl = "Index/Address/getAddress";
                queryAddress(hashMap,interfaceUrl,string);


                break;

        }

    }


    private void queryAddress(HashMap hashMap, String interfaceUrl, final String s) {

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

                String code = null;
                try {
                    code = new JSONObject(response).getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(code.equals("100000")) {

                    final Address address = (Address) JsonDecoder.decode(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("type", address.getType());
                            bundle.putString("name", address.getName());
                            bundle.putString("phonenum", address.getPhonenum());
                            bundle.putString("postcode", address.getPostcode());
                            bundle.putString("area", address.getArea());
                            bundle.putString("details", address.getDetails());
                            bundle.putString(TAG,"0");
                            bundle.putString(TAG1,s);

                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }else {
                    Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(TAG,"1");
                    bundle.putString(TAG1,s);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }





            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CartActivity.this, "收货地址异常", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
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
//
//                }
//
//            });



            return view;


        }
    }
}
