package com.cloud.orderservice.controller;

import com.cloud.orderservice.dao.OrderDAO;
import com.cloud.orderservice.infoqueue.Producer;
import com.cloud.orderservice.pojo.Order;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: OrderController
 * @author: Yinan Cheng
 */
@RestController
public class OrderController {
    @Autowired
    OrderDAO orderDAO;
    private Producer producer;

    @ApiOperation("获得系统中所有的订单信息")
    @GetMapping("/orders")
    public List<Order> listOrder() throws Exception {
        List<Order> cs=orderDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

    @ApiOperation("用户 确认订单 后还未支付时调用")
    @PostMapping("/addOrder")
    public void addOrder(@RequestBody Order c) throws Exception {
        //c.setStatus("unpaid");
        orderDAO.save(c);
        System.out.println(c.getId());
    }

    @ApiOperation("用户 支付成功 后调用")   //未完成
    @PostMapping("/payOrder")
    public void payOrder(@RequestBody Order c) throws Exception {
        //producer.sendMessage(c);
        producer.sendMessage("连接成功啦啦啦啦");
    }

    @ApiOperation("用户 未支付时取消订单 时调用")  //未测试
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) throws Exception {
        Order o=orderDAO.getOne(id);
        orderDAO.delete(o);
    }
}
