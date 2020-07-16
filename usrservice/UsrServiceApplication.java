package com.example.usrservice;

import com.example.usrservice.database.RedisService;
import com.example.usrservice.database.UserDao;
import com.example.usrservice.entry.*;
import com.example.usrservice.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class UsrServiceApplication {

    @Autowired
    UserDao userDao;
    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(UsrServiceApplication.class, args);
    }

    @RequestMapping("/init")
    public String test2(){
        System.out.println("init data");
        String name1 = "Yama";
        String name2 = "Yamaa";
        String password = "213100";
        password = MyPasswordEncoder.getEncoder().encode(password);
        SystemUser systemUser = new SystemUser(name1, password, "admin");
        System.out.println("是否为null: " + systemUser.getUsr_id() == null);
        userDao.save(systemUser);
        userDao.save(new SystemUser(name2, password, "user"));

        return "加密后的密码" + password;
    }

    @RequestMapping("/init2")
    public void test(){
        BrowseHistory bh = new BrowseHistory(4);
        OrderHistory oh = new OrderHistory(4);
        Usr_Loc ul = new Usr_Loc(4);
        String book_name = "book_name";
        String book_pic = "picture";
        String price = "price";

        String rec = "receiver";
        String phone = "phone";
        String province = "province";
        String city = "city";
        String area = "area";
        String town = "town";
        String loc = "locloc";


        for(int i=0; i<5; ++i){
            Date date = new Date();
            //BrowseData browseData = new BrowseData(i, book_pic+i, book_name+i, price+i, date);
            //bh.addBrowseData(browseData);
            oh.addOrder(i);
            ul.addLoc(new LocInfor(rec+i, phone+i, province+i, city+i, area+i, town+i, loc+i));
        }
        System.out.println("bh = "+ bh);
        redisService.addBrowseHistory(bh);
        System.out.println("oh = " + oh);
        redisService.addOrderHistory(oh);
        redisService.updateLoc(ul);

    }

//    @RequestMapping("/signin")
//    public boolean signin(@RequestBody Map params) throws Exception {
//        String name = (String) params.get("name");
//        String password = (String) params.get("password");
//        SystemUser systemUser = userDao.findByName(name);
//        if(systemUser != null){
//            return false;
//        }
//        password = MyPasswordEncoder.getEncoder().encode(password);
//        userDao.save(new SystemUser(name, password, "user"));
//        return true;
//    }
}
