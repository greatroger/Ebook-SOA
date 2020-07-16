package com.example.usrservice.entry;

import java.io.Serializable;

public class OrderData implements Serializable {
    int ord_id;
    String total;
    String book_id;
    String picture;
    String title;
    String price;
    int num;
    String status;

    public void setOrd_id(int ord_id) {
        this.ord_id = ord_id;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrd_id() {
        return ord_id;
    }

    public String getTotal() {
        return total;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public String getStatus() {
        return status;
    }

    public OrderData(int ord_id, String total, String book_id, String picture, String title, String price, int num, String status) {
        this.ord_id = ord_id;
        this.total = total;
        this.book_id = book_id;
        this.picture = picture;
        this.title = title;
        this.price = price;
        this.num = num;
        this.status = status;
    }
}
