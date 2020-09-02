package com.demon.jetpackroom.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 种类 - 实体
 * @date : 2019/11/29
 */
@Data
@Entity(tableName = "Category")
public class Category {

    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    /**
     * 种类名称
     */
    @ColumnInfo(name = "category_name")
    private String categoryName;
    /**
     * 种类号码
     */
    @ColumnInfo(name = "category_code")
    private int categoryCode;

    public Category() {
    }

    @Ignore
    public Category(int id, String categoryName, int categoryCode) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

}
