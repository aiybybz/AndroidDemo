package com.demon.jetpackroom.dao;

import com.demon.jetpackroom.entity.Category;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 种类 - 数据库操作
 * @date : 2019/11/29
 */
@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Category> bookList);

    @Delete
    int delete(Category category);

    @Delete
    int deleteAll(List<Category> categoryList);

    @Update()
    int update(Category category);

    @Update()
    int updateAll(Category... categories);

    @Query("SELECT * FROM Category ")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE id = :cid")
    List<Category> findByUid(int cid);

}
