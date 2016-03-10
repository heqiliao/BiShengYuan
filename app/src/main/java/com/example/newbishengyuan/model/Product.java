package com.example.newbishengyuan.model;

/**
 * Created by 何弃疗 on 2015/8/8.
 */
public class Product {
    private String product_id;
    private String classification_id;
    private String seller_id;
    private String name;
    private String price;
    private String member_price;
    private String VIP_price;
    private String default_url;
    private String stock;
    private String sold_amount;
    private String instruction;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getClassification_id() {
        return classification_id;
    }

    public void setClassification_id(String classification_id) {
        this.classification_id = classification_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMember_price() {
        return member_price;
    }

    public void setMember_price(String member_price) {
        this.member_price = member_price;
    }

    public String getVIP_price() {
        return VIP_price;
    }

    public void setVIP_price(String VIP_price) {
        this.VIP_price = VIP_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDefault_url() {
        return default_url;
    }

    public void setDefault_url(String default_url) {
        this.default_url = default_url;
    }

    public String getSold_amount() {
        return sold_amount;
    }

    public void setSold_amount(String sold_amount) {
        this.sold_amount = sold_amount;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
