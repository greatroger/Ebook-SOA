package com.cloud.categoryservice.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "client-bookService")
public interface BookFeign {
    @Autowired
    BookFeign bookFeign = null;

}
