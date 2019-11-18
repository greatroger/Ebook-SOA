package com.cloud.orderservice.infoqueue;

import com.cloud.orderservice.pojo.Order;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @className: Producer
 * @author: Yinan Cheng
 */
@Service("producer")
public class Producer {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;
    // 发送消息，destination是发送到的队列，message是待发送的消息
    private Destination destination = new ActiveMQQueue("mytest.queue");
//    public void sendMessage(final Order message){
//        jmsTemplate.convertAndSend(destination, message);
//    }

    public void sendMessage(final String message){
        jmsTemplate.convertAndSend(destination, message);
    }
}
