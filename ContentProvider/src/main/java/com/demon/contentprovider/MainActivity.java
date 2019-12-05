package com.demon.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demon.contentprovider.db.DbHelper;
import com.demon.contentprovider.entity.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : ContentProvider(内容提供者) Demo
 * @date : 2019/12/5 15:01
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_data)
    TextView tvData;

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        DbHelper dbHelper = new DbHelper(this, "BookStore.db", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    @OnClick({R.id.add_data, R.id.update_data, R.id.delete_data, R.id.query_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 添加数据
            case R.id.add_data:
                insert();
                break;
            // 更新数据
            case R.id.update_data:
                update();
                break;
            // 删除数据
            case R.id.delete_data:
                delete();
                break;
            // 查询数据
            case R.id.query_data:
                query();
                break;
            default:
                break;
        }
    }

    private void insert() {
        // 组装插入第一条数据
        ContentValues values1 = new ContentValues();
        values1.put("author", "张一");
        values1.put("price", 16.96);
        values1.put("pages", 454);
        values1.put("name", "张一书");
        db.insert("Book", null, values1);

        // 组装插入第二条数据
        ContentValues values2 = new ContentValues();
        values2.put("author", "张二");
        values2.put("price", 40.5);
        values2.put("pages", 656);
        values2.put("name", "张二书");
        db.insert("Book", null, values2);
    }

    private void update() {
        // 组装更新第一条数据
        ContentValues values1 = new ContentValues();
        values1.put("price", 30.5);
        db.update("Book", values1, "author = ?", new String[]{"张一"});

        // 组装更新第二条数据
        ContentValues values2 = new ContentValues();
        values2.put("price", 60.5);
        db.update("Book", values2, "author = ?", new String[]{"张二"});
    }

    private void delete() {
        db.delete("Book", "pages > ?", new String[]{"600"});
    }

    private void query() {
        List<Book> bookList = new ArrayList<>();

        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                bookList.add(new Book(id, author, price, pages, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        tvData.setText(bookList.toString());
    }

}