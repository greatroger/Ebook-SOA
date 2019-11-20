package com.cloud.orderservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @className: Order
 * @author: Yinan Cheng
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name = "order_")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userid;

    @Column(name = "bookid")
    private int bookid;

    @Column(name = "status")
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getBookid() {
        return bookid;
    }

    public int getUserid() {
        return userid;
    }

    public String getStatus() {
        return status;
    }
}
