package com.example.recommend_service.entity;

public class SaleData {
    //数据库名称
    public static String HIGH_LEVEL = "high_level";
    public static String LOW_LEVEL = "low_level";

    //列名称
    public static String BOOKID = "book_id";
    public static String SALES = "sales";

    public int book_id;
    public int sales;

    public SaleData(int book_id, int sales){
        this.book_id = book_id;
        this.sales = sales;
    }
}
