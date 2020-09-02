package com.demon.jetpackroom.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 书 - 实体
 * @date : 2019/11/29
 */
@Data
@Entity(tableName = "Book")
public class Book {

    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    /**
     * 作者
     */
    @ColumnInfo(name = "author")
    private String author;
    /**
     * 单价
     */
    @ColumnInfo(name = "price")
    private double price;
    /**
     * 页数
     */
    @ColumnInfo(name = "pages")
    private int pages;
    /**
     * 书名
     */
    @ColumnInfo(name = "name")
    private String name;

    public Book() {
    }

    @Ignore
    public Book(int id, String author, double price, int pages, String name) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.name = name;
    }

}
