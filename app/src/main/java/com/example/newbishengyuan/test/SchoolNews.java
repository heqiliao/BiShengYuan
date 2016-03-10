package com.example.newbishengyuan.test;

import java.io.Serializable;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class SchoolNews implements Serializable {
    private String imageId;

    public SchoolNews(String imageId, String content, String title) {
        this.imageId = imageId;
        this.content = content;
        this.title = title;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String title;
    private String content;
}
