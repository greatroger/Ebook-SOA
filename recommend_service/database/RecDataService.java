package com.example.recommend_service.database;

import com.example.recommend_service.entity.BookSaleData;
import com.example.recommend_service.entity.BookSimpleData;
import com.example.recommend_service.entity.SaleData;
import com.example.recommend_service.util.HttpHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.recommend_service.database.Sql.GETBOOKDATA;

@Service
public class RecDataService {
    public static RecDataService recDataService;
    private static String bookDataUrl = "http://59.110.160.154:8040/api-search/v1/book-simple-data";
    public static int level = 1000;

    @PostConstruct
    public void init(){
        recDataService = this;
        recDataService.jdbcTemplate = this.jdbcTemplate;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getRecommendBookData(){
        Gson gson = new Gson();
        System.out.println("获取推荐书籍信息sql语句： "+ GETBOOKDATA);
        List<BookSimpleData> result = jdbcTemplate.query(GETBOOKDATA, new RowMapper<BookSimpleData>() {
            @Override
            public BookSimpleData mapRow(ResultSet resultSet, int i) throws SQLException {
                int book_id = resultSet.getInt(BookSimpleData.BOOKID);
                String book_name = resultSet.getString(BookSimpleData.BOOKNAME);
                String picture = resultSet.getString(BookSimpleData.PICTURE);
                String price = resultSet.getString(BookSimpleData.PIRCE);

                return new BookSimpleData(book_id, picture, book_name, price);
            }
        });
        System.out.println("书籍推荐信息： "+ result);
        return gson.toJson(result);
    }

    //更新书籍信息
    public void updateHighLevel(){
        Map<Integer, BookSaleData> bookData = new HashMap<>();
        Gson gson = new Gson();
        System.out.println("查询书籍信息sql语句: "+ Sql.GETBOOKID);
        List<Integer> bookIDList = jdbcTemplate.query(Sql.GETBOOKID, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                int book_id = -1;
                try {
                    book_id = resultSet.getInt(SaleData.BOOKID);
                    int sales = resultSet.getInt(SaleData.SALES);

                    bookData.put(book_id, new BookSaleData(sales));
                    return book_id;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return book_id;
            }
        });
        System.out.println("查询书籍： "+ bookIDList);

        String result = null;
        try{
            result = HttpHandler.doPost(bookDataUrl, gson.toJson(bookIDList));
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("查询结果: "+ result);

        try {
            Type dataListType = new TypeToken<ArrayList<BookSimpleData>>(){}.getType();
            List<BookSimpleData> bdl = gson.fromJson(result, dataListType);

            for (BookSimpleData bsd : bdl) {
                bookData.get(bsd.book_id).setBookSimpleData(bsd);
            }

            System.out.println("删除书籍信息数据");
            jdbcTemplate.execute(Sql.DELETEBOOKDATA);

            System.out.println("插入书籍信息");
            for (BookSaleData bookSaleData : bookData.values()) {
                Object[] args = {bookSaleData.getBookID(), bookSaleData.getBookName(), bookSaleData.getPicture(), bookSaleData.getPrice(), bookSaleData.getSales()};
                System.out.println("插入书籍： " + gson.toJson(bookSaleData));
                jdbcTemplate.update(Sql.INSERTBOOKDATA, args);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String test(){
        Gson gson = new Gson();
        List<Map<String, Object>> result =
                jdbcTemplate.queryForList("SELECT * FROM recommend");
        System.out.println("Service Class test: "+ result);
        return gson.toJson(result);
    }

    public void addSaleData(int book_id, int sales){
        Object[] args = {book_id, sales};
        System.out.println("添加书籍销量信息:"+args);
        try {
            if (sales > level) {
                jdbcTemplate.update(Sql.INSERTSALESDATA_H, args);
            } else {
                jdbcTemplate.update(Sql.INSERTSALESDATA_L, args);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSaleData(int book_id){
        String sql = "delete from high_level where book_id = ?";
        String sql2 = "delete from low_level where book_id = ?";
        int result = jdbcTemplate.update(sql, book_id);
        if(result == 0){
            jdbcTemplate.update(sql2, book_id);
        }
    }

    public void deleteSaleData(List<Integer> bookIDList){
        for(int i=0; i<bookIDList.size(); ++i){
            deleteSaleData(bookIDList.get(i));
        }
    }

    public void updateSaleData(int book_id, int incre){
        String findSql = "select * from high_level where book_id = ?";
        Object[] findArgs = {book_id};
        SaleData result = jdbcTemplate.queryForObject(findSql, findArgs, new BeanPropertyRowMapper<>(SaleData.class));
        if(result == null){
            findSql = "select * from low_level where book_id = ?";
            result = jdbcTemplate.queryForObject(findSql, findArgs, new BeanPropertyRowMapper<>(SaleData.class));
        }else{
            String sql = "update high_level set sales=? where book_id = ?";
            Object[] args = {incre+result.sales, book_id};
            jdbcTemplate.update(sql, args);
        }
        if(result != null){
            incre += result.sales;
            deleteSaleData(book_id);
            addSaleData(book_id, incre);
        }
        System.out.println("book_id:"+book_id+" 没有销量信息");
    }
}
