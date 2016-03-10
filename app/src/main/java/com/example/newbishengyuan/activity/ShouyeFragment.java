package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Announcement;
import com.example.newbishengyuan.model.Blog;
import com.example.newbishengyuan.model.Customer;
import com.example.newbishengyuan.model.JsonDecoder;
import com.example.newbishengyuan.model.News;
import com.example.newbishengyuan.model.SystemLetter;
import com.example.newbishengyuan.test.SchoolNews;
import com.example.newbishengyuan.util.HttpCallbacklistener;
import com.example.newbishengyuan.util.HttpUtil;
import com.example.newbishengyuan.util.MyApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by 何弃疗 on 2015/7/23.
 */
public class ShouyeFragment extends Fragment implements View.OnClickListener{
    private Button wodezijin;
    private Button shangmaosanbao;
    private Button zongbangshangcheng;
    private Button yueba;
    private Button tiaozaoshichang;
    private Button kudichaxun;
    private Button zhinenggongjiao;
    private Button jingcaihuodong;
    private TabHost tabHost;
    private String customerId;

    private View view;
    private FrameLayout systemLetter;
    private PtrClassicFrameLayout inprogressPtr;
    private TextView reportText;
    private TextView title;
    private TextView date;
    private TextView content;
    private ImageView photo;
    private TextView more;

    private MyApplication myApplication = MyApplication.getInstance();

    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private ViewPager advPager = null;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;
    private static  String TAG = "home";

    private  String announcement;
    private String newsTitle;
    private String newsContent;
    private String newsTime;
    private String picture;
    private String newsId;
    private String adminId;
    private String time;

    AdvAdapter advAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      view = inflater.inflate(R.layout.shouye_fragment, container, false);
        customerId = myApplication.getCustomerId();

        wodezijin = (Button) view.findViewById(R.id.wodezijin);
        shangmaosanbao = (Button) view.findViewById(R.id.shangmaosanbao);
        zongbangshangcheng = (Button) view.findViewById(R.id.zhongbangshangcheng);
        yueba = (Button) view.findViewById(R.id.yue);
        tiaozaoshichang = (Button) view.findViewById(R.id.tiaozaoshichang);
        kudichaxun = (Button) view.findViewById(R.id.courier);
        zhinenggongjiao = (Button) view.findViewById(R.id.gongjiao);
        jingcaihuodong = (Button) view.findViewById(R.id.wonderful);
        more = (TextView) view.findViewById(R.id.more);
        systemLetter = (FrameLayout) view.findViewById(R.id.message);
        reportText = (TextView) view.findViewById(R.id.reportText);
        title = (TextView) view.findViewById(R.id.id1);
        date = (TextView) view.findViewById(R.id.date);
        content = (TextView) view.findViewById(R.id.content);
        photo = (ImageView) view.findViewById(R.id.photo);



        inprogressPtr = (PtrClassicFrameLayout) view.findViewById(R.id.inprogress_ptr);

        initViewPager();
        initHomePage();

//        inprogressPtr.setPtrHandler(new PtrHandler() {
//
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
//            }
//
//            @Override
//            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
//                ptrFrameLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ptrFrameLayout.refreshComplete();
//                    }
//                }, 1800);
//
//            }
//        });

        wodezijin.setOnClickListener(this);
        shangmaosanbao.setOnClickListener(this);
        zongbangshangcheng.setOnClickListener(this);
        yueba.setOnClickListener(this);
        tiaozaoshichang.setOnClickListener(this);
        kudichaxun.setOnClickListener(this);
        zhinenggongjiao.setOnClickListener(this);
        jingcaihuodong.setOnClickListener(this);
        more.setOnClickListener(this);
        systemLetter.setOnClickListener(this);
        photo.setOnClickListener(this);


        return view;
    }


    private void initHomePage() {

        HashMap hashMap = new HashMap();
        String interfaceUrl = "Index/Index/index";

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject json1 = json.getJSONObject("result");
                    JSONObject json2 = json1.getJSONObject("Announcement");

                    announcement = json2.getString("content");
                    JSONObject json3 = json1.getJSONObject("News");
                    newsTitle = json3.getString("title");
                    newsContent = json3.getString("content");
                    newsTime = json3.getString("time");
                    picture = json3.getString("picture");
                    newsId = json3.getString("news_id");
                    adminId = json3.getString("admin_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time = format.format(Integer.parseInt(newsTime));

                        reportText.setText(announcement);
                        title.setText(newsTitle);
                        date.setText(time);
                        content.setText(newsContent);
                        if (picture != null) {
                            ImageLoader.getInstance().displayImage(picture,
                                    photo);

//                            ImageLoader.getInstance().loadImage(picture, new SimpleImageLoadingListener() {
//
//                                @Override
//                                public void onLoadingComplete(String imageUri, View view,
//                                                              Bitmap loadedImage) {
//                                    super.onLoadingComplete(imageUri, view, loadedImage);
//                                    photo.setImageBitmap(loadedImage);
////                                      Toast.makeText(SumActivity.this, "首页界面信息成功", Toast.LENGTH_SHORT).show();
//                                }
//
//                            });

                        }
                    }
                });



            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "首页界面信息异常", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    //    广告滚动栏
    private void initViewPager() {
        advPager = (ViewPager) view.findViewById(R.id.adv_pager);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup);

//      这里存放的是四张广告背景
        List<View> advPics = new ArrayList<View>();
        ImageView img0 = new ImageView(getActivity());
        img0.setBackgroundResource(R.mipmap.adv3);
        advPics.add(img0);
        ImageView img1 = new ImageView(getActivity());
        img1.setBackgroundResource(R.mipmap.bar);
        advPics.add(img1);

        ImageView img2 = new ImageView(getActivity());
        img2.setBackgroundResource(R.mipmap.adv1);
        advPics.add(img2);

        ImageView img3 = new ImageView(getActivity());
        img3.setBackgroundResource(R.mipmap.adv2);
        advPics.add(img3);

        ImageView img4 = new ImageView(getActivity());
        img4.setBackgroundResource(R.mipmap.weifangchan);
        advPics.add(img4);

        ImageView img5 = new ImageView(getActivity());
        img5.setBackgroundResource(R.mipmap.adv3);
        advPics.add(img5);

        ImageView img6 = new ImageView(getActivity());
        img6.setBackgroundResource(R.mipmap.bar);
        advPics.add(img6);

//      对imageviews进行填充
        imageViews = new ImageView[advPics.size()-2];
//小图标
        for (int i = 0; i < advPics.size()-2; i++) {
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.ball1);
            } else {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.ball2);
            }
            group.addView(imageViews[i]);
        }
advAdapter = new AdvAdapter(advPics);
        advPager.setAdapter(new AdvAdapter(advPics));
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (true) {
//                    if (isContinue) {
//                        viewHandler.sendEmptyMessage(what.get());
//                        whatOption();
//                    }
//                }
//            }
//
//        }).start();
       final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int count = advAdapter.getCount();

                if(count>2&&isContinue){
                    int index = advPager.getCurrentItem();
                    index = index%(count-2)+1;
                    advPager.setCurrentItem(index,false);
//                    index++;
                }






                handler.postDelayed(this, 2 * 1000);

            }
        };

        handler.postDelayed(runnable, 2 * 1000);

    }


    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-5);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }


    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }


        @Override
        public void onPageSelected(int position) {
//            what.getAndSet(arg0);
//            for (int i = 0; i < imageViews.length; i++) {
//                imageViews[arg0]
//                        .setBackgroundResource(R.mipmap.ball1);
//                if (arg0 != i) {
//                    imageViews[i]
//                            .setBackgroundResource(R.mipmap.ball2);
//                }
//            }
            if(position<1) {
                position=imageViews.length;
                advPager.setCurrentItem(position,false);
            }else if(position>imageViews.length){
                advPager.setCurrentItem(1,false);
                position = 1;
            }

            for (int i = 0; i < imageViews.length; i++) {
                if (i == position-1) {
                    System.out.println("true" + i);
                    imageViews[i].setBackgroundResource(R.mipmap.ball1);

                } else {

                    imageViews[i].setBackgroundResource(R.mipmap.ball2);

                }
            }

        }

    }

    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){



            case R.id.wodezijin:


                if(TextUtils.isEmpty(customerId)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else {


                HashMap hashMap3 = new HashMap();
//                hashMap.put("","");
                String interfaceUrl = "Index/Customer/getInfo";
                queryPersonaInfo(hashMap3, interfaceUrl);
                }

                break;
            case R.id.shangmaosanbao:

                if(TextUtils.isEmpty(customerId)) {

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    tabHost.setCurrentTab(1);
                }
                break;
            case R.id.zhongbangshangcheng:
                tabHost.setCurrentTab(2);
                break;
            case R.id.gongjiao:
                Intent intent1= new Intent(getActivity(),GongJiaoActivity.class);
                startActivity(intent1);
                break;
            case R.id.more:
                HashMap hashMap = new HashMap();

                hashMap.put("page","0");
                hashMap.put("num", "10");
                String interfaceUrl = "Index/News/getNewsList";
                queryPersonaInfo(hashMap, interfaceUrl);
                getMore(hashMap,interfaceUrl);

                break;
            case R.id.yue:
                HashMap yuehMap = new HashMap();
                yuehMap.put("category_id", "3");
                yuehMap.put("page", "0");
                yuehMap.put("num", "10");
                String yueInterfacuUrl = "Index/Blog/getBlogList";
                toTiaozao(yuehMap, yueInterfacuUrl);
                break;
            case R.id.wonderful:
                HashMap wonderfulHashMap = new HashMap();
                wonderfulHashMap.put("category_id","2");
                wonderfulHashMap.put("page","0");
                wonderfulHashMap.put("num","10");
                String wonderfulInterfacuUrl = "Index/Blog/getBlogList";
                toTiaozao(wonderfulHashMap, wonderfulInterfacuUrl);
                break;
            case R.id.tiaozaoshichang:
               HashMap tiaoHashMap = new HashMap();
                tiaoHashMap.put("category_id","1");
                tiaoHashMap.put("page","0");
                tiaoHashMap.put("num","10");
                String tiaoInterfacuUrl = "Index/Blog/getBlogList";
                toTiaozao(tiaoHashMap,tiaoInterfacuUrl);
                break;
            case R.id.courier:
                Intent intent6 = new Intent(getActivity(),CourierActivity.class);
                startActivity(intent6);
                break;
            case R.id.message:
                HashMap hashMap1 = new HashMap();
                hashMap1.put("status","all");
                hashMap1.put("page","0");
                hashMap1.put("num", "10");
                String interfaceUrl1 = "Index/Letter/getLetters";
               getMsg(hashMap1, interfaceUrl1);
                break;
            case R.id.photo:

             Intent intent = new Intent(getActivity(),ZiXunXiangQingActivity.class);
                intent.putExtra("title",newsTitle);
                intent.putExtra("admin",adminId);
                intent.putExtra("date",time);
                intent.putExtra("photo",picture);
                intent.putExtra("content",newsContent);
                startActivity(intent);
                break;
        }

    }

    private void toTiaozao(HashMap tiaoHashMap, String tiaoInterfacuUrl) {
        new HttpUtil().sendHttpRequest(tiaoHashMap, tiaoInterfacuUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
          List<Blog> list = (List<Blog>) JsonDecoder.decode(response);

                Intent intent = new Intent(getActivity(),TiaoZaoActivity.class);
                intent.putExtra("good",(Serializable)list);
                startActivity(intent);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void yue(HashMap yueHashMap, String yueInterfaceUrl1) {
    new HttpUtil().sendHttpRequest(yueHashMap, yueInterfaceUrl1, new HttpCallbacklistener() {
        @Override
        public void onFinish(String response) {

        }

        @Override
        public void onError(Exception e) {

        }
    });
    }


    private void getMore(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
                List<News> systemLetterList = (List<News>) JsonDecoder.decode(response);
                Intent intent = new Intent(getActivity(),SchoolAreaActivity.class);
                intent.putExtra("good",(Serializable)systemLetterList);
                startActivity(intent);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    private void getMsg(HashMap hashMap, String interfaceUrl) {
        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl, new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {
             if(TextUtils.isEmpty(myApplication.getCustomerId())){
                 getActivity().runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(getActivity(),LoginActivity.class);
                         startActivity(intent);
                     }
                 });
             }
            List<SystemLetter> systemLetterList = (List<SystemLetter>) JsonDecoder.decode(response);
                Intent intent = new Intent(getActivity(),SystemLetterActivity.class);
                intent.putExtra("good",(Serializable)systemLetterList);
                startActivity(intent);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        tabHost = ((SumActivity)getActivity()).getTabHost();
    }


    private void queryPersonaInfo(HashMap hashMap, String interfaceUrl) {

        new HttpUtil().sendHttpRequest(hashMap, interfaceUrl,new HttpCallbacklistener() {
            @Override
            public void onFinish(String response) {


                Customer customer = (Customer) JsonDecoder.decode(response);
                String customer_id = customer.getCustomer_id();
                final  String username = customer.getUsername();
                final String realname = customer.getRealname();
                final  String sex= customer.getSex();
                final  String phonenum = customer.getPhonenum();
                String email = customer.getEmail();
                final  String idcard = customer.getIdcard();
                final String student_number = customer.getStudent_number();
                final String department = customer.getDepartment();
                String grade = customer.getGrade();
                final  String student_class = customer.getStudent_class();
                String remark =customer.getRemark();
                String status = customer.getStatus();
                String picture = customer.getPicture();
                final String name = customer.getName();
                String balance = customer.getBalance();
                String sid = customer.getSid();
//                    myApplication.setCustomerId(customer_id);
//                    myApplication.setStatus(status);
//                    myApplication.setImageUrl(picture);
//                    myApplication.setName(name);
//                myApplication.set
//                    myApplication.setBalance(balance);
//                    myApplication.setSid(sid);


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "查询顾客信息成功", Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("realname",realname);
                        bundle.putString("sex",sex);
                        bundle.putString("department",department);
                        bundle.putString("phonenum",phonenum);
                        bundle.putString("idcard",idcard);
                        bundle.putString("student_number",student_number);
                        Intent intent = new Intent(getActivity(), MyAccountActivity.class);
                        intent.putExtras(bundle);


                        startActivity(intent);
                    }
                });



            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getActivity(), "查询顾客信息异常", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
