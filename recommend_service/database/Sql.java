package com.example.recommend_service.database;

import com.example.recommend_service.entity.BookSaleData;
import com.example.recommend_service.entity.SaleData;

public class Sql {
    public static String GETBOOKDATA = "SELECT * FROM "+ BookSaleData.RECOMMENDBOOKDATA;
//    public static String GETINCRE = "SELECT " + Increase.INCRE +" FROM " + Increase.INCREASE +
//            "WHERE " + Increase.BOOKID +" = ";
    public static String GETBOOKID = "SELECT *" + " FROM " + SaleData.HIGH_LEVEL +
            " ORDER BY " + SaleData.SALES + " LIMIT 10";

    //删除书籍信息
    public static String DELETEBOOKDATA = "DELETE FROM " + BookSaleData.RECOMMENDBOOKDATA;

    //插入书籍信息
    public static String INSERTBOOKDATA = "INSERT INTO " + BookSaleData.RECOMMENDBOOKDATA +
            " VALUES (?, ?, ?, ?, ?)";

    //插入销量信息到高销量表中
    public static String INSERTSALESDATA_H = "INSERT INTO " + SaleData.HIGH_LEVEL +
            " VALUES (?,?)";

    //插入销量信息到高销量表中
    public static String INSERTSALESDATA_L = "INSERT INTO " + SaleData.LOW_LEVEL +
            " VALUES (?,?)";
}
