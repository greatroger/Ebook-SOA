package com.example.usrservice.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements Serializable {
    public int getUsr_id() {
        return usr_id;
    }

    int usr_id;
    List<Integer> orderList = new ArrayList<>();

    public List<Integer> getOrderList() {
        return orderList;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public void setOrderList(List<Integer> orderList) {
        this.orderList = orderList;
    }

    public int getHistoryNum(){
        return orderList.size();
    }

    public OrderHistory(int id){
        this.usr_id = id;
    }

    public OrderHistory(int id, List<Integer> orderList){
        this.usr_id = id;
        this.orderList = orderList;
    }

    public void addOrder(int order_id){
        orderList.add(order_id);
    }
}
