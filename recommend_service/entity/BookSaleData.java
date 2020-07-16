package com.example.recommend_service.entity;

public class BookSaleData {
    public static String RECOMMENDBOOKDATA = "book_data";
    public static String SALES = "sales";

    public BookSimpleData bookSimpleData;
    public int sales;

    public BookSaleData(int sales){
        this.sales = sales;
    }

    public void setBookSimpleData(BookSimpleData bookSimpleData) {
        this.bookSimpleData = bookSimpleData;
    }

    public int getBookID(){
        return bookSimpleData.book_id;
    }

    public String getBookName(){
        return bookSimpleData.book_name;
    }

    public String getPicture(){
        return bookSimpleData.picture;
    }

    public String getPrice(){
        return bookSimpleData.price;
    }

    public int getSales(){
        return sales;
    }
}
