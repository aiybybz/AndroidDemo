package com.demon.jetpackroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demon.jetpackroom.dao.BookDao;
import com.demon.jetpackroom.dao.CategoryDao;
import com.demon.jetpackroom.database.BaseAppDatabase;
import com.demon.jetpackroom.entity.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainTvResult)
    TextView mainTvResult;
    private BaseAppDatabase db;

    private BookDao bDao;
    private CategoryDao cDao;

    /**
     * 线程池
     */
    private ThreadManager.ThreadPool threadPool = ThreadManager.getThreadPool();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDatabase();
    }

    private void initDatabase() {
        db = Room.databaseBuilder(
                getApplication(),
                BaseAppDatabase.class,
                "room-database"
        )
                // 添加数据库变动迁移
                //.addMigrations(AppDatabase.MIGRATION_1_2)
                // 下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                // 他可能造成主线程lock以及anr
                // 所以我们的操作都是在新线程完成的
                // .allowMainThreadQueries()
                .build();
        bDao = db.bookDao();
        cDao = db.categoryDao();
    }

    @OnClick({R.id.mainBtnAddOne, R.id.mainBtnAddSome, R.id.mainBtnDeleteOne, R.id.mainBtnDeleteSome, R.id.mainBtnUpdateOne, R.id.mainBtnUpdateSome, R.id.mainBtnQueryOne, R.id.mainBtnQuerySome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 增加单条
            case R.id.mainBtnAddOne:
                threadPool.execute(() -> {
                    Long result = bDao.insert(new Book(1, "张三", 11.1, 100, "张三书"));
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(MainActivity.this, "【新增单条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 增加多条
            case R.id.mainBtnAddSome:
                int dataCount = 100;
                List<Book> books = new ArrayList<>();
                for (int i = 2; i <= dataCount; i++) {
                    books.add(new Book(i, "张三" + i, 11.1 + i, 100 + i, "张三书" + i));
                }
                threadPool.execute(() -> {
                    List<Long> result = bDao.insertAll(books);
                    runOnUiThread(() -> {
                        if (result.size() > 0) {
                            Toast.makeText(MainActivity.this, "【新增多条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 删除单条
            case R.id.mainBtnDeleteOne:
                threadPool.execute(() -> {
                    int result = bDao.delete(new Book(1, "张三", 11.1, 100, "张三书"));
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(MainActivity.this, "【删除单条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 删除多条
            case R.id.mainBtnDeleteSome:
                int dataCountSome = 100;
                List<Book> booksSome = new ArrayList<>();
                for (int i = 2; i <= dataCountSome; i++) {
                    booksSome.add(new Book(i, "张三" + i, 11.1 + i, 100 + i, "张三书" + i));
                }
                threadPool.execute(() -> {
                    int result = bDao.deleteAll(booksSome);
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(MainActivity.this, "【删除多条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 修改单条
            case R.id.mainBtnUpdateOne:
                threadPool.execute(() -> {
                    int result = bDao.update(new Book(1, "张三", 11.1, 100, "张三书修订版"));
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(MainActivity.this, "【修改单条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 修改多条
            case R.id.mainBtnUpdateSome:
                int dataCountUp = 100;
                List<Book> booksUp = new ArrayList<>();
                for (int i = 2; i <= dataCountUp; i++) {
                    booksUp.add(new Book(i, "张三" + i, 11.1 + i, 100 + i, "张三书修订版" + i));
                }
                threadPool.execute(() -> {
                    int result = bDao.updateAll(booksUp);
                    runOnUiThread(() -> {
                        if (result > 0) {
                            Toast.makeText(MainActivity.this, "【修改多条】 成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                break;
            // 查询单条
            case R.id.mainBtnQueryOne:
                threadPool.execute(() -> {
                    List<Book> result = bDao.findByUid(1);
                    runOnUiThread(() -> mainTvResult.setText(result.toString()));
                });
                break;
            // 查询全部
            case R.id.mainBtnQuerySome:
                threadPool.execute(() -> {
                    List<Book> result = bDao.getAll();
                    runOnUiThread(() -> mainTvResult.setText(result.toString()));
                });
                break;
            default:
                break;
        }
    }


}
