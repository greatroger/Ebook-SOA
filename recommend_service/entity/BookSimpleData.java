package com.example.recommend_service.entity;

public class BookSimpleData {
    public static String BOOKID = "book_id";
    public static String PICTURE = "picture";
    public static String BOOKNAME  = "book_name";
    public static String PIRCE = "price";

    public int book_id;
    public String picture;
    public String book_name;
    public String price;

    public BookSimpleData(int id, String picture, String name, String price){
        book_id = id;
        this.picture = picture;
        this.book_name = name;
        this.price = price;
    }
}
