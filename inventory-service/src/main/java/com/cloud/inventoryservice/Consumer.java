package com.cloud.inventoryservice;

import com.cloud.orderservice.pojo.Order.*;

import com.cloud.orderservice.pojo.Order;
import org.springframework.jms.annotation.JmsListener;

/**
 * @className: Consumer
 * @author: Yinan Cheng
 */
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }
//    public void receiveQueue(Order text) {
//        System.out.println("Consumer收到的报文为:"+text);
//    }
}
