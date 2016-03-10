package com.example.newbishengyuan.test;

import java.io.Serializable;

/**
 * Created by 何弃疗 on 2015/7/24.
 */
public class Good implements Serializable{
    private String name;
    private String product_id;
    private String classification_id;

    public String getClassification_id() {
        return classification_id;
    }

    public void setClassification_id(String classification_id) {
        this.classification_id = classification_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Good(String product_id,String name, String price_before, String price,String amount, String imageId,String xiaoliang,String classification_id) {
        this.product_id = product_id;

        this.name = name;
        this.price_before = price_before;
        this.price = price;
        this.amount=amount;
        this.imageId = imageId;
        this.xiaoliang = xiaoliang;
        this.classification_id = classification_id;
    }

    private String price_before;
    private String price;
    private String amount;
    private String imageId;
    private String xiaoliang;


    public String getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(String xiaoliang) {
        this.xiaoliang = xiaoliang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_before() {
        return price_before;
    }

    public void setPrice_before(String price_before) {
        this.price_before = price_before;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

//    public int getImageId() {

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

//        return imageId;
//    }
//
//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }
}
