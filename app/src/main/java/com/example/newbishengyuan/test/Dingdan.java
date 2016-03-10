package com.example.newbishengyuan.test;

/**
 * Created by 何弃疗 on 2015/8/9.
 */
public class Dingdan {
    private String number;
    private String amount;
    private String order_time;
    private String status;
    private String address;
    private String delivery_time;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Dingdan(String number, String amount, String order_time, String status, String address, String delivery_time) {
        this.number = number;
        this.amount = amount;
        this.order_time = order_time;

        this.status = status;
        this.address = address;
        this.delivery_time = delivery_time;
    }
}
