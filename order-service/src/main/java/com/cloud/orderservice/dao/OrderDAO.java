package com.cloud.orderservice.dao;

import com.cloud.orderservice.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer> {
    @Query(nativeQuery = true, value ="select * from order_ where userid=:userid and status=:status")
    List getCartByUser(@Param("userid")int userid,@PathVariable("status") String status);
}
