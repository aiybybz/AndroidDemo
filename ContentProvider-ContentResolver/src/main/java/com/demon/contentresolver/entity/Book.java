package com.demon.contentresolver.entity;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 书籍 - 实体
 * @date : 2019/11/29
 */
public class Book {

    /**
     * 主键
     */
    private int id;
    /**
     * 作者
     */
    private String author;
    /**
     * 单价
     */
    private double price;
    /**
     * 页数
     */
    private int pages;
    /**
     * 书名
     */
    private String name;

    public Book() {
    }

    public Book(int id, String author, double price, int pages, String name) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
