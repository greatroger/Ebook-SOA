package com.example.recommend_service.util;

import com.example.recommend_service.database.RecDataService;
import com.example.recommend_service.entity.BookSimpleData;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataIniter {
    static int bookNum = 2795;

    static public void initData(){
        Random random = new Random();
        for(int i=0; i<bookNum; ++i){
            int sales = random.nextInt(1200);
            System.out.println("book_id = "+i + " sales = " + sales);
            RecDataService.recDataService.addSaleData(i, sales);
        }
    }
}
