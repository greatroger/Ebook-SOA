package com.cloud.seachservice.dao;

import com.cloud.seachservice.pojo.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookDAO extends JpaRepository<BookInfo,Integer> {
    public List<BookInfo> findByCategoryid(int categoryid);
    public List<BookInfo> findByAuthorLike(String author);
    public  List<BookInfo> findByNameLike(String name);
}
