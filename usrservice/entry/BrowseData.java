package com.example.usrservice.entry;

import java.io.Serializable;
import java.util.Date;

public class BrowseData implements Serializable {
    public static String BOOKID = "id";
    public static String PICTURE = "picurl";
    public static String BOOKNAME  = "name";
    public static String PIRCE = "price";

    public int getBook_id() {
        return book_id;
    }

    public String getPicture() {
        return picture;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int book_id;
    public String picture;
    public String book_name;
    public String price;
    public String date;

    public BrowseData(int id, String picture, String book_name, String price, String date){
        book_id = id;
        this.picture = picture;
        this.book_name = book_name;
        this.price = price;
        this.date = date;
    }
}
