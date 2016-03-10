package com.example.newbishengyuan.model;

import java.io.Serializable;

/**
 * Created by 何弃疗 on 2015/8/2.
 */
public class FinanceProduct implements Serializable{
    private String product_id;
    private String name;
    private String type;
    private String yield;
    private String cycle;
    private String duration;
    private String pattern;

    public String getProduct_id() {
        return product_id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getYield() {
        return yield;
    }

    public String getDuration() {
        return duration;
    }

    public String getCycle() {
        return cycle;
    }

    public String getPattern() {
        return pattern;
    }
}
