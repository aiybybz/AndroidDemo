package com.demon.jetpackroom.dao;

import com.demon.jetpackroom.entity.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 书 - 数据库操作
 * @date : 2019/11/29
 */
@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Book book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Book> bookList);

    @Delete
    int delete(Book book);

    @Delete
    int deleteAll(List<Book> bookList);

    @Update()
    int update(Book book);

    @Update()
    int updateAll(List<Book> books);

    @Query("SELECT * FROM Book ")
    List<Book> getAll();

    @Query("SELECT * FROM Book WHERE id = :bid")
    List<Book> findByUid(int bid);

}
