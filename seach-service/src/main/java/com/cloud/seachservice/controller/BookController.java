package com.cloud.seachservice.controller;

import com.cloud.seachservice.dao.BookDAO;
import com.cloud.seachservice.pojo.BookInfo;
import com.cloud.seachservice.pojo.BookSimpleData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: BookController
 * @author: Yinan Cheng
 */
@RestController
public class BookController {
    @Autowired
    BookDAO bookDAO;

//    @ApiOperation("获得系统中所有的书籍信息")
//    @GetMapping("/v1/books")
//    public List<BookInfo> listBook() throws Exception {
//        List<BookInfo> cs=bookDAO.findAll();
//        System.out.println("findAll");
//        return cs;
//    }

    @ApiOperation("获得分类ID为categoryid的所有书籍信息（已弃用）")
    @GetMapping("/v1/book-category/{categoryid}")
    public List<BookInfo> categoryBook(@PathVariable("categoryid") int categoryid) throws Exception{
        List<BookInfo> cs=bookDAO.findByCategoryid(categoryid);
        return cs;
    }

    @ApiOperation("根据用户输入的关键字，搜索书籍")
    @GetMapping("/v1/book-consultance/{keyWord}")
    public List<BookInfo> searchBook(@PathVariable("keyWord") String keyWord) throws Exception{
        List<BookInfo> cs1=bookDAO.findByNameLike(keyWord);
        List<BookInfo> cs2=bookDAO.findByAuthorLike(keyWord);
        cs2.addAll(cs1);
        return cs2;
    }

    @ApiOperation("获得书籍ID为id的书籍信息")
    @GetMapping("/v1/book/{id}")
    @HystrixCommand(fallbackMethod="findByBookIdFallback")
    public BookInfo searchBookID(@PathVariable("id") Integer id) throws Exception{
        BookInfo b=bookDAO.getOne(id);
        return b;
    }

    public BookInfo findByBookIdFallback(Integer id){
        BookInfo book=new BookInfo();
        book.setId(0);
        book.setName("NULL");
        book.setTranslator("NULL");
        book.setPublisher("NULL");
        book.setPrice("NULL");
        book.setPicurl("NULL");
        book.setAuthor("NULL");
        book.setCategoryid(0);
        book.setDescription("NULL");
        book.setDoubanscore("NULL");
        return book;
    }

    @ApiOperation("分页获得分类ID为categoryid的书籍信息")
    @GetMapping("/v1/book-page/{categoryid}/{pageid}")
    public Page pageBookCategory(@PathVariable("pageid") int pageid,@PathVariable("categoryid") int categoryid)throws Exception{
        Pageable pageReques= PageRequest.of(pageid, 20);
        Page<BookInfo> page=bookDAO.getByPaged(pageReques,categoryid);
        return page;
    }

    @ApiOperation("管理员新增平台书籍")
    @PostMapping("/v1/book-addition")
    public boolean addBook(@RequestBody BookInfo c) throws Exception {
        bookDAO.save(c);
        System.out.println(c.getId());
        return true;
    }

    @ApiOperation("管理员修改平台书籍信息,但是id不可以修改")
    @PutMapping("/v1/book-modify/{id}")
    public void changeBookInfo(@PathVariable("id") Integer id, @RequestBody BookInfo b) throws Exception {
        BookInfo c= bookDAO.getOne(id);
        c.setAuthor(b.getAuthor());
        c.setCategoryid(b.getCategoryid());
        c.setDescription(b.getDescription());
        c.setName(b.getName());
        c.setPicurl(b.getPicurl());
        c.setPrice(b.getPrice());
        c.setPublisher(b.getPublisher());
        c.setTranslator(b.getTranslator());
        bookDAO.save(c);
    }

    @ApiOperation("管理员删除平台某书籍")
    @DeleteMapping("/v1/book-deletion/{id}")
    public void deleteBook(@PathVariable("id") Integer id) throws Exception {
        BookInfo c= bookDAO.getOne(id);
        bookDAO.delete(c);
    }

//    @ApiOperation("分页获得全部书籍信息")
//    @GetMapping("/v1/book-page/{pageid}")
//    public Page pageBook(@PathVariable("pageid") int pageid) throws Exception{
//        Pageable pageReques= PageRequest.of(pageid, 20);
//        Page<BookInfo> page=bookDAO.findAll(pageReques);
//        return page;
//    }

    @ApiOperation("根据一串ID为id的书籍信息（供后端使用）")
    @PostMapping("/v1/book-simple-data")
    public List<BookSimpleData> getBooksDataByID(@RequestBody List<Integer> BookIDList){
        List<BookSimpleData> bookSimpleDataList = new ArrayList<>();
        for(int i : BookIDList){
            bookSimpleDataList.add(new BookSimpleData(bookDAO.getOne(i)));
        }
        return bookSimpleDataList;
    }

}
