package com.example.usrservice.database;

import com.example.usrservice.entry.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class RedisService {

    static String LOC_TABLE = "loc_";
    static String BRO_TABLE = "bro_";
    static String ORD_TABLE = "ord_";
    public static RedisService redisService;
    public static int browseNum = 20;
    public static int orderNum = 20;

    @PostConstruct
    public void init(){
        redisService = this;
        redisService.redisTemplate = this.redisTemplate;
    }

    @Autowired
    RedisTemplate redisTemplate;

    public void updateLoc(Usr_Loc usr_loc){
        //Gson gson = new Gson();
        //String loc = gson.toJson(usr_loc.getLocList());
        redisTemplate.opsForList().leftPushAll(LOC_TABLE+usr_loc.getUsr_id(), usr_loc.getLocList());
    }

    public void addBrowseHistory(BrowseHistory browseHistory){
        String key = BRO_TABLE+browseHistory.getUser_id();
        System.out.println("key = "+key);
        long size = redisTemplate.opsForList().size(key) + browseHistory.getHistoryNum();
        redisTemplate.opsForList().leftPushAll(key, browseHistory.getBrowseDataList());
        if(size > browseNum){
            redisTemplate.opsForList().trim(key, 0, 20);
        }
    }

    public void addOrderHistory(OrderHistory orderHistory){
        String key = ORD_TABLE+orderHistory.getUsr_id();
        long size = redisTemplate.opsForList().size(key) + orderHistory.getHistoryNum();
        redisTemplate.opsForList().leftPushAll(key, orderHistory.getOrderList());
        if(size > orderNum){
            redisTemplate.opsForList().trim(key, 0, 20);
        }
    }

    public List<LocInfor> getLoc(int id){
        List<LocInfor> result = null;
        result = redisTemplate.opsForList().range(LOC_TABLE + id, 0, -1);
        return result;
    }

    public List<BrowseData> getBrowseList(int id){
        List<BrowseData> result = null;
        try {
            System.out.println("search key = " + BRO_TABLE + id);
            result = redisTemplate.opsForList().range(BRO_TABLE + id, 0, -1);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("browse result: " + result);
        return result;
    }

    public List<Integer> getOrderList(int id){
        List<Integer> result = null;
        try {
            result = redisTemplate.opsForList().range(ORD_TABLE + id, 0, -1);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("order result: " + result);
        return result;
    }

}
