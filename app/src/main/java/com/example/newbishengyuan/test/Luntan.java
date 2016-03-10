package com.example.newbishengyuan.test;

import java.io.Serializable;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class Luntan implements Serializable {
    private String picture;
    private String name;
    private String time;
    private String summary;
    private String head;
    private String comment_count;
    private String praise_count;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(String praise_count) {
        this.praise_count = praise_count;
    }

    public Luntan(String picture, String name, String time, String head, String summary, String comment_count, String praise_count) {

        this.picture = picture;
        this.name = name;
        this.time = time;
        this.head = head;
        this.summary = summary;
        this.comment_count = comment_count;
        this.praise_count = praise_count;
    }
}
