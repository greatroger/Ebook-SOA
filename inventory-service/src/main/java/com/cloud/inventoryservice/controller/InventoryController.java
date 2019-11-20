package com.cloud.inventoryservice.controller;

import com.cloud.inventoryservice.dao.InventoryDAO;
import com.cloud.inventoryservice.pojo.Inventory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.jms.Message;
import java.util.*;

/**
 * @className: InventoryController
 * @author: Yinan Cheng
 */
@RestController
public class InventoryController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    InventoryDAO inventoryDAO;

    @Autowired
    private RestTemplate template;

//    @JmsListener(destination = "mytest.queue")
//    public void pollOrder(String bookid){
//        Integer i=Integer.valueOf(bookid);
//        Inventory inventory=inventoryDAO.getOne(i);
//        System.out.println("查找到书籍："+inventory.getBookid());
//        if (inventory.getStock()>0){
//            int stockNum=inventory.getStock();
//            inventory.setStock(stockNum-1);
//            inventoryDAO.save(inventory);
//        }
//        else{ //向管理员发消息，可用API轮询或webSocket实现
//            try {
//                lackBook(i);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @JmsListener(destination = "mytest.queue")
    public void pullOrder(HashMap<String,String> map){
        String orderid_s = null;
        String bookid_s=null;
        Set<String> key = map.keySet();
        for (String string : key) {
            orderid_s=string;  //取出键
            break;
        }
        Collection<String> connection = map.values();
        Iterator<String> iterator = connection.iterator();
        while (iterator.hasNext()) {
            bookid_s=iterator.next();
            break;
        }
        System.out.println(orderid_s + "  "+bookid_s);
        Integer bookid=Integer.valueOf(bookid_s);
        Inventory inventory=inventoryDAO.getOne(bookid);
        Integer orderid=Integer.valueOf(orderid_s);
        System.out.println("查找到书籍："+inventory.getBookid());
        if (inventory.getStock()>0){
            int stockNum=inventory.getStock();
            inventory.setStock(stockNum-1);
            inventoryDAO.save(inventory);
            template.put("http://127.0.0.1:8083/v1/order-shipping/"+orderid,null);
        }
    }

    @ApiOperation("给管理员发送缺书提示，需轮询调用")
    @GetMapping("/lackBook")
    public String lackBook (Integer bookid) throws Exception {
        return "缺少书籍ID为"+bookid+"的书籍，请补充";
    }

    @ApiOperation("获取全部库存信息")
    @GetMapping("/inventories")
    public List<Inventory> listInventory() throws Exception {
        List<Inventory> cs=inventoryDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

}
