package com.cloud.seachservice.dao;

import com.cloud.seachservice.pojo.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookDAO extends JpaRepository<BookInfo,Integer> {
    public List<BookInfo> findByCategoryid(int categoryid);
    public List<BookInfo> findByAuthorLike(String author);
    public List<BookInfo> findByNameLike(String name);

    @Query(nativeQuery = true, value ="select * from book_ where categoryid=:categoryid")
    Page getByPaged(Pageable pageable, @Param("categoryid")int categoryid);
}
