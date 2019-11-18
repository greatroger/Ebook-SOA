package com.cloud.categoryservice.dao;

import com.cloud.categoryservice.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
