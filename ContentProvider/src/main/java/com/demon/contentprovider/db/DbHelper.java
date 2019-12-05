package com.demon.contentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 数据库帮助类
 * @date : 2019/12/5 13:52
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book( " +
            "    id integer primary key autoincrement, " +
            "    author text, " +
            "    price real, " +
            "    pages integer, " +
            "    name text) ";


    private Context mContext;


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }

}
