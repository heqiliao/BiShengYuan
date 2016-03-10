package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.News;
import com.example.newbishengyuan.model.SystemLetter;
import com.example.newbishengyuan.test.Msg;
import com.example.newbishengyuan.test.MsgAdapter;
import com.example.newbishengyuan.test.SchoolNews;
import com.example.newbishengyuan.test.SchoolNewsAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/27.
 */
public class SchoolAreaActivity extends BaseActivity {
    private List<News> systemLetterList = new ArrayList<>();
    private List<SchoolNews> msgList = new ArrayList<>();
    private SimpleDateFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolarea);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(Integer.parseInt(newsTime));

        systemLetterList = (List<News>) getIntent().getSerializableExtra("good");
        intiMsg();
        SchoolNewsAdapter goodAdapter = new SchoolNewsAdapter(this, R.layout.school_item, msgList);
        ListView listView = (ListView) findViewById(R.id.newsList);
        listView.setAdapter(goodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = systemLetterList.get(position);

                Intent intent = new Intent(SchoolAreaActivity.this,ZiXunXiangQingActivity.class);
                intent.putExtra("title",news.getTitle());
                intent.putExtra("admin",news.getAdmin_id());
                intent.putExtra("date",format.format(Integer.parseInt(news.getTime())));
                intent.putExtra("photo",news.getPicture());
                intent.putExtra("content",news.getSummary());
                startActivity(intent);
            }
        });

    }

    private void intiMsg() {
        for (News systemLetter : systemLetterList) {
            SchoolNews msg = new SchoolNews(systemLetter.getPicture(), systemLetter.getSummary(), systemLetter.getTitle());
            msgList.add(msg);
        }

    }
}
