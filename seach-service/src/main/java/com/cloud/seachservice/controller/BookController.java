package com.cloud.seachservice.controller;

import com.cloud.seachservice.dao.BookDAO;
import com.cloud.seachservice.pojo.BookInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: BookController
 * @author: Yinan Cheng
 */
@RestController
public class BookController {
    @Autowired
    BookDAO bookDAO;

    @ApiOperation("获得系统中所有的书籍信息")
    @GetMapping("/books")
    public List<BookInfo> listBook() throws Exception {
        List<BookInfo> cs=bookDAO.findAll();
        System.out.println("findAll");
        return cs;
    }

    @ApiOperation("获得分类ID为categoryid的所有书籍信息")
    @GetMapping("/bookcategory/{categoryid}")
    public List<BookInfo> categoryBook(@PathVariable("categoryid") int categoryid) throws Exception{
        List<BookInfo> cs=bookDAO.findByCategoryid(categoryid);
        return cs;
    }

    @ApiOperation("根据用户输入的关键字，搜索书籍")
    @GetMapping("/searchBook/{keyWord}")
    public List<BookInfo> searchBook(@PathVariable("keyWord") String keyWord) throws Exception{
        List<BookInfo> cs1=bookDAO.findByNameLike(keyWord);
        List<BookInfo> cs2=bookDAO.findByAuthorLike(keyWord);
        cs2.addAll(cs1);
        return cs2;
    }

    @ApiOperation("获得书籍ID为id的书籍信息")
    @GetMapping("/book/{id}")
    public BookInfo searchBookID(@PathVariable("id") Integer id) throws Exception{
        BookInfo b=bookDAO.getOne(id);
        return b;
    }


}
