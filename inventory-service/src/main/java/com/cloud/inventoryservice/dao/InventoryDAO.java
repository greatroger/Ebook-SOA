package com.cloud.inventoryservice.dao;

import com.cloud.inventoryservice.pojo.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryDAO extends JpaRepository<Inventory,Integer> {
    @Query(nativeQuery = true, value ="select * from inventory_ where stock<20")
    List lackWarn();
}
