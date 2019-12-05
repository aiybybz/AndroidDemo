package com.demon.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.demon.contentprovider.db.DbHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : ContentProvider(内容提供器)
 * @date : 2019/11/26
 */
public class DbProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;

    public static final String AUTHORITY = "com.demon.content.provider";

    private static UriMatcher uriMatcher;

    private DbHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "Book/#", BOOK_ITEM);
    }


    /**
     * 初始化
     * 通常在此完成对数据库的创建和升级等操作，true：初始化成功、false：失败。
     * 注意：只有当存在ContentResolver尝试访问我们程序中的数据时，内容提供器才会被初始化。
     */
    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext(), "BookStore.db", null, 1);
        return true;
    }

    /**
     * 查询数据
     * @param uri           确定查询哪张表中的数据
     * @param projection    查询哪些列
     * @param selection     查询哪些行
     * @param selectionArgs 查询列占位符具体值
     * @param sortOrder     排序
     * @return Cursor
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // 查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            // 查询 Book 表中的所有数据
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            // 查询 Book 表中的单条数据
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    /**
     * 添加数据
     * @param uri    确定添加到的表
     * @param values 待添加的数据
     * @return 返回一个用于表示这条新记录的URI
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // 添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    /**
     * 更新数据
     * @param uri           确定更新哪张表中的数据
     * @param values        新数据
     * @param selection     更新约束
     * @param selectionArgs 约束占位符具体值
     * @return 受影响行数
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        // 更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book", values, "id = ?", new String[]{bookId});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    /**
     * 删除数据
     * @param uri           确定删除哪张表中的数据
     * @param selection     删除约束
     * @param selectionArgs 约束占位符具体值
     * @return 受影响行数
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // 删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book", "id = ?", new String[]{bookId});
                break;
            default:
                break;
        }
        return deletedRows;
    }

    /**
     * 类型
     * @param uri ContentResolver 的增删改查时传递过来的 内容UIR
     * @return vnd
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // 根据传入的内容URI来返回相应的MIME类型。
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.demon.contentprovider.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.demon.contentprovider.provider.book";
            default:
                return null;
        }
    }

}