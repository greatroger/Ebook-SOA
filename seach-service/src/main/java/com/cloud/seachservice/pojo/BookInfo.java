package com.cloud.seachservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @className: Book
 * @author: Yinan Cheng
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name = "book_")
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @Column(name = "picurl")
    private String picurl;

    @Column(name = "author")
    private String author;

    @Column(name = "translator")
    private String translator;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "categoryid")
    private int categoryid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicurl() {
        return picurl;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryid() {
        return categoryid;
    }

        public String getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTranslator() {
        return translator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
