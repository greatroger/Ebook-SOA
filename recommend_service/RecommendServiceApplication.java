package com.example.recommend_service;

import com.example.recommend_service.database.RecDataService;
import com.example.recommend_service.entity.SaleData;
import com.example.recommend_service.util.DataIniter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class RecommendServiceApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RecommendServiceApplication.class, args);
        //DataIniter.initData();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                RecDataService.recDataService.updateHighLevel();
            }
        }, 0, 60, TimeUnit.MINUTES);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/test1")
    public String test(@RequestParam(value="key", defaultValue = "type") String key){
        return RecDataService.recDataService.test();
    }

    @GetMapping("/v1/recommend-book-data")
    public String getRecommendBook(){
        return RecDataService.recDataService.getRecommendBookData();
    }

    @DeleteMapping("/v1/delete-recommend-data")
    public void deleteSalesData(@RequestBody List<Integer> bookIDList){
        RecDataService.recDataService.deleteSaleData(bookIDList);
    }

    @PostMapping("/v1/recommend-book-data")
    public void addSalesData(@RequestBody SaleData saleData){
        RecDataService.recDataService.addSaleData(saleData.book_id, saleData.sales);
    }

}
