package com.cloud.orderservice.dao;

import com.cloud.orderservice.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {

}
