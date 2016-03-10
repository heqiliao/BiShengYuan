package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Blog;
import com.example.newbishengyuan.model.News;
import com.example.newbishengyuan.test.Luntan;
import com.example.newbishengyuan.test.LuntanAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class TiaoZaoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView edit;

    private List<Blog> blogArrayList = new ArrayList<>();
    private List<Luntan> luntanList = new ArrayList<>();
    private SimpleDateFormat format;
    private ListView listView;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiaozaoshichang);
        edit = (ImageView) findViewById(R.id.edit);
        title = (TextView) findViewById(R.id.title);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        listView = (ListView) findViewById(R.id.list);
        blogArrayList = (List<Blog>) getIntent().getSerializableExtra("good");
        if(blogArrayList.get(0).getCategory_id().equals("1")){
            title.setText("跳蚤市场");
        }
        if(blogArrayList.get(0).getCategory_id().equals("2")){
            title.setText("精彩活动");
        }
        if(blogArrayList.get(0).getCategory_id().equals("3")){
            title.setText("约吧");
        }
        init();
        LuntanAdapter luntanAdapter = new LuntanAdapter(this,R.layout.tiaozaoitem,luntanList);
        listView.setAdapter(luntanAdapter);

        edit.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TiaoZaoActivity.this,HuaTiXiangQingActivity.class);
                startActivity(intent);
            }
        });
    }
//    public Luntan(String picture, String name, String time, String head, String summary, String comment_count, String praise_count) {
//
//        this.picture = picture;
//        this.name = name;
//        this.time = time;
//        this.head = head;
//        this.summary = summary;
//        this.comment_count = comment_count;
//        this.praise_count = praise_count;
//    }
    private void init() {

        for (Blog blog:blogArrayList){
            Luntan luntan = new Luntan(blog.getPicture(),blog.getName(),format.format(Integer.parseInt(blog.getTime())),blog.getHead(),blog.getSummary(),blog.getComment_count(),blog.getPraise_count());
            luntanList.add(luntan);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                Intent intent = new Intent(this,EditTiaoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
