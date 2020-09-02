package com.demon.jetpackroom.database;

import com.demon.jetpackroom.dao.BookDao;
import com.demon.jetpackroom.dao.CategoryDao;
import com.demon.jetpackroom.entity.Book;
import com.demon.jetpackroom.entity.Category;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 数据库创建
 * @date : 2019/11/29
 */
@Database(entities = {Book.class, Category.class}, version = 1, exportSchema = false)
public abstract class BaseAppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract CategoryDao categoryDao();


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // 数据库的具体变动
            // 类型是integer，不为空，默认值是0
            database.execSQL("ALTER TABLE Book ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE Category ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
        }
    };

}
