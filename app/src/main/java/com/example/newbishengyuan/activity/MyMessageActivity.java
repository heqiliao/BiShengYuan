package com.example.newbishengyuan.activity;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.ListView;

        import com.example.newbishengyuan.R;
        import com.example.newbishengyuan.test.Msg;
        import com.example.newbishengyuan.test.MsgAdapter;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by 何弃疗 on 2015/7/26.
 */
public class MyMessageActivity extends BaseActivity {
    private List<Msg> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage);
        intiMsg();
        MsgAdapter goodAdapter = new MsgAdapter(this,R.layout.message_item,list);
        ListView listView = (ListView) findViewById(R.id.message);
        listView.setAdapter(goodAdapter);

    }

    private void intiMsg() {
        Msg msg1 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg1);
        Msg msg2 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg2);
        Msg msg3 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg3);
        Msg msg4 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg4);
        Msg msg5 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg5);
        Msg msg6 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg6);
        Msg msg7 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg7);
        Msg msg8 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg8);
        Msg msg9 = new Msg("","如果您的计算机或网络受到防火墙或者代理服务器的保护，请确认 Firefox 已被授权访问网络","2015-7-26");
        list.add(msg9);

    }


}
