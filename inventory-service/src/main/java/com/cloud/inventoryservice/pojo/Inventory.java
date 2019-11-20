package com.cloud.inventoryservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @className: Inventory
 * @author: Yinan Cheng
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Proxy(lazy = false)
@Table(name = "inventory_")
public class Inventory {
    @Id
    @Column(name = "bookid")
    private int bookid;

    @Column(name = "stock")
    private int stock;

    public int getBookid() {
        return bookid;
    }

    public int getStock() {
        return stock;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
