package com.example.newbishengyuan.model;

/**
 * Created by 何弃疗 on 2015/8/7.
 */
public class Address {
    private String address_id;
    private String type;
    private String name;
    private String phonenum;
    private String postcode;
    private String area;


    public String getDetails() {
        return details;
    }

    public String getArea() {
        return area;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress_id() {
        return address_id;
    }

    private String details;

}
