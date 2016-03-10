package com.example.newbishengyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.newbishengyuan.R;
import com.example.newbishengyuan.model.Order;
import com.example.newbishengyuan.model.SystemLetter;
import com.example.newbishengyuan.test.Good;
import com.example.newbishengyuan.test.Msg;
import com.example.newbishengyuan.test.MsgAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何弃疗 on 2015/8/6.
 */
public class SystemLetterActivity extends Activity {
    private List<SystemLetter> systemLetterList = new ArrayList<>();
    private List<Msg> msgList = new ArrayList<>();
    private SimpleDateFormat format;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage);
      format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(Integer.parseInt(newsTime));

        systemLetterList = (List<SystemLetter>) getIntent().getSerializableExtra("good");
        intiMsg();
        MsgAdapter goodAdapter = new MsgAdapter(this,R.layout.message_item,msgList);
        ListView listView = (ListView) findViewById(R.id.message);
        listView.setAdapter(goodAdapter);

    }

    private void intiMsg() {
      for(SystemLetter systemLetter:systemLetterList){
          Msg msg = new Msg("",systemLetter.getContent(),format.format(Integer.parseInt(systemLetter.getTime())));
          msgList.add(msg);
      }

    }
}
