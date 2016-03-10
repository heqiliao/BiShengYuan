package com.example.newbishengyuan.test;

/**
 * Created by 何弃疗 on 2015/7/26.
 */
public class Msg {
    private String imageId;
    private String date;
    private String content;

    public Msg(String imageId, String content, String date) {
        this.imageId = imageId;
        this.content = content;
        this.date = date;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
}
