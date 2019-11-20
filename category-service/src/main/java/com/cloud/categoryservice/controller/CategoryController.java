package com.cloud.categoryservice.controller;

import com.cloud.categoryservice.dao.CategoryDAO;
import com.cloud.categoryservice.pojo.Category;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;

/**
 * @className: CategoryController
 * @author: Yinan Cheng
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;

    @ApiOperation("获得全部的书籍分类")
    @GetMapping("/v1/categories")  //获得系统中所有书籍的分类
    public List<Category> listCategory() throws Exception {
        List<Category> cs=categoryDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

}
