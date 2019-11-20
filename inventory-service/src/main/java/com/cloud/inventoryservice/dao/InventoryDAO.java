package com.cloud.inventoryservice.dao;

import com.cloud.inventoryservice.pojo.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDAO extends JpaRepository<Inventory,Integer> {
}
