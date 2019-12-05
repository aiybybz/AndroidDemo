package com.demon.contentresolver;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demon.contentresolver.entity.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 内容解析器 Demo，ContentProvider
 * @date : 2019/12/5 10:07
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_data)
    TextView tvData;

    /**
     * 内容URI
     */
    String contentUri = "content://com.demon.content.provider/Book";
    /**
     * 添加的Id
     */
    private String newId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_add_data, R.id.tv_query_data, R.id.tv_update_data, R.id.tv_delete_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 添加数据
            case R.id.tv_add_data:
                insert();
                break;
            // 查询数据
            case R.id.tv_query_data:
                query();
                break;
            // 更新数据
            case R.id.tv_update_data:
                update();
                break;
            // 删除数据
            case R.id.tv_delete_data:
                delete();
                break;
            default:
                break;
        }
    }

    /**
     * 添加数据
     */
    private void insert() {
        // 1.解析接收到的 内容URI
        Uri uri = Uri.parse(contentUri);
        // 2.通过 ContentValues 组装数据
        ContentValues values = new ContentValues();
        values.put("author", "跨进程 -【新增】作者：张三");
        values.put("price", 10.1);
        values.put("pages", 300);
        values.put("name", "跨进程通信");
        // 3.插入数据
        Uri newUri = getContentResolver().insert(uri, values);
        if (newUri != null) {
            newId = newUri.getPathSegments().get(1);
        }
    }

    /**
     * 查询数据
     */
    private void query() {
        List<Book> bookList = new ArrayList<>();

        // 1.解析接收到的 内容URI
        Uri uri = Uri.parse(contentUri);
        // 2.获取 Cursor 对象
        Cursor cursor = getContentResolver().query(uri, new String[]{"id", "author", "price", "pages", "name"}, null, null, null);
        // 3.移动游标遍历 Cursor
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 列--值
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                bookList.add(new Book(id, author, price, pages, name));
            }
            cursor.close();
        }
        tvData.setText(bookList.toString());
    }

    /**
     * 更新数据
     */
    private void update() {
        // 1.解析接收到的 内容URI
        Uri uri = Uri.parse(contentUri + "/" + newId);
        // 2.通过 ContentValues 组装数据
        ContentValues values = new ContentValues();
        values.put("author", "跨进程 -【更新】作者：张三");
        values.put("price", 20.1);
        values.put("pages", 400);
        values.put("name", "跨进程通信");
        // 3.更新数据
        getContentResolver().update(uri, values, null, null);
    }

    /**
     * 删除数据
     */
    private void delete() {
        // 1.解析接收到的 内容URI
        Uri uri = Uri.parse(contentUri + "/" + newId);
        // 2.删除数据
        getContentResolver().delete(uri, null, null);
    }

}
