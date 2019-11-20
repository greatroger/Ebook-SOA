package com.cloud.orderservice.controller;

import com.cloud.orderservice.dao.OrderDAO;
import com.cloud.orderservice.pojo.Order;
import io.swagger.annotations.ApiOperation;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.List;

/**
 * @className: OrderController
 * @author: Yinan Cheng
 */
@RestController
public class OrderController {
    @Autowired
    OrderDAO orderDAO;

    @Autowired
    private JmsTemplate jmsTemplate;

    //private Destination destination = new ActiveMQQueue("mytest.queue");

    @ApiOperation("获得系统中所有的订单信息")
    @GetMapping("/v1/orders")
    public List<Order> listOrder() throws Exception {
        List<Order> cs=orderDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

    @ApiOperation("用户 确认订单 后还未支付时调用")
    @PostMapping("/v1/order-addition")
    public boolean addOrder(@RequestBody Order c) throws Exception {
        orderDAO.save(c);
        System.out.println(c.getId());
        return true;
    }

    @ApiOperation("用户 支付成功，意图在于把这条订单信息放入库存消息队列")
    @PostMapping("/v1/order-payment")
    public void payOrder(@RequestBody String bookid) throws Exception {
        jmsTemplate.convertAndSend(bookid);
    }

    @ApiOperation("用户 支付成功，意图在于把这条订单信息放入库存消息队列")
    @PostMapping("/v2/order-payment")
    public void addToQueue(@RequestBody Order o)throws Exception {
        int bookid=o.getBookid();
        int orderid=o.getId();
        HashMap<String,String>orderInfo=new HashMap<String, String>();
        String orderid_s=String.valueOf(orderid);
        String bookid_s=String.valueOf(bookid);
        orderInfo.put(orderid_s,bookid_s);
        jmsTemplate.convertAndSend(orderInfo);
    }

    @ApiOperation("用户 支付成功，把某一个订单的状态从 unpaid 更改为 paid")
    @PutMapping("/v1/paid-order/{id}")
    public void changeOrderStatus(@PathVariable("id") Integer id) throws Exception {
        Order c= orderDAO.getOne(id);
        c.setStatus("paid");
        orderDAO.save(c);
        System.out.println(c.getId()+c.getStatus());
    }

    @ApiOperation("用户 未支付时取消订单 或 库存缺货经管理员决定给用户推单 时调用")
    @DeleteMapping("/v1/order-deletion/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) throws Exception {
        Order o=orderDAO.getOne(id);
        orderDAO.delete(o);
    }

    @ApiOperation("把订单的状态更改为 shipping")
    @PutMapping("/v1/order-shipping/{id}")
    public void shipOrder(@PathVariable("id") Integer id) throws Exception {
        Order o=orderDAO.getOne(id);
        o.setStatus("shipping");
        orderDAO.save(o);
        System.out.println(o.getId()+o.getStatus());
    }

    @ApiOperation("货物送达后，把某一个订单的状态从 paid 更改为 finished")
    @PutMapping("/v1/finished-order/{id}")
    public void finishOrder(@PathVariable("id") Integer id) throws Exception {
        Order c= orderDAO.getOne(id);
        c.setStatus("finished");
        orderDAO.save(c);
        System.out.println(c.getId()+c.getStatus());
    }
}
