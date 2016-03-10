package com.example.newbishengyuan.model;

import java.io.Serializable;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class Blog implements Serializable {
    private String blog_id;
    private String category_id;
    private String customer_id;
    private String name;
    private String head;
    private String title;
    private String summary;
    private String picture;
    private String time;
    private String comment_count;
    private String praise_count;

    public String getBlog_id() {
        return blog_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getHead() {
        return head;
    }

    public String getSummary() {
        return summary;
    }

    public String getPicture() {
        return picture;
    }

    public String getTime() {
        return time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public String getPraise_count() {
        return praise_count;
    }
}
