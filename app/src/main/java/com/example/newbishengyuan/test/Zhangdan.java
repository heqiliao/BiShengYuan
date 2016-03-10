package com.example.newbishengyuan.test;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class Zhangdan  {
    private String title;
    private String date;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Zhangdan(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;

    }
}
