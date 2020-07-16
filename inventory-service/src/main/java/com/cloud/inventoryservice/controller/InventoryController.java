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
//    public void pullOrder(HashMap<String,String> map){
//        String orderid_s = null;
//        String bookid_s=null;
//        Set<String> key = map.keySet();
//        for (String string : key) {
//            orderid_s=string;  //取出键
//            break;
//        }
//        Collection<String> connection = map.values();
//        Iterator<String> iterator = connection.iterator();
//        while (iterator.hasNext()) {
//            bookid_s=iterator.next();
//            break;
//        }
//        System.out.println(orderid_s + "  "+bookid_s);
//        Integer bookid=Integer.valueOf(bookid_s);
//        Inventory inventory=inventoryDAO.getOne(bookid);
//        Integer orderid=Integer.valueOf(orderid_s);
//        System.out.println("查找到书籍："+inventory.getBookid());
//        if (inventory.getStock()>0){
//            int stockNum=inventory.getStock();
//            inventory.setStock(stockNum-1);
//            inventoryDAO.save(inventory);
//            template.put("http://127.0.0.1:8083/v1/order-shipping/"+orderid,null);
//        }
//    }

    @JmsListener(destination = "mytest.queue")
    public void pullOrder(HashMap<String,String> map){
        String orderid_s = null;
        String bookid_info=null;
        Set<String> key = map.keySet();
        for (String string : key) {
            orderid_s=string;  //取出键
            break;
        }
        Collection<String> connection = map.values();
        Iterator<String> iterator = connection.iterator();
        while (iterator.hasNext()) {
            bookid_info=iterator.next();
            break;
        }
        int count=0;
        for(int i=0;i<bookid_info.length();i++){
            char ch = bookid_info.charAt(i);
            count++;
            if (ch=='+'){
                break;
            }
        }
        String book_id=bookid_info.substring(0,count-1);
        System.out.println("书籍ID:"+book_id);
        String book_num=bookid_info.substring(count,bookid_info.length());
        System.out.println("书籍数量:"+book_num);
        Integer bookid=Integer.valueOf(book_id);
        Integer booknum=Integer.valueOf(book_num);
        Inventory inventory=inventoryDAO.getOne(bookid);
        Integer orderid=Integer.valueOf(orderid_s);
        if (inventory.getStock()>booknum){
            int stockNum=inventory.getStock();
            inventory.setStock(stockNum-booknum);
            inventoryDAO.save(inventory);
            template.put("http://127.0.0.1:8083/v1/order-shipping/"+orderid,null);
        }
    }

    @ApiOperation("获取全部库存信息")
    @GetMapping("/inventories")
    public List<Inventory> listInventory() throws Exception {
        List<Inventory> cs=inventoryDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

    @ApiOperation("获取缺货信息")
    @GetMapping("/lack-book")
    public List<Inventory> lackBook() throws Exception {
        List<Inventory> cs=inventoryDAO.lackWarn();
        return cs;
    }

}
