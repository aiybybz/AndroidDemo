package com.demon.service;

import android.os.Bundle;
import android.view.View;

import com.demon.service.background.BackgroundActivity;
import com.demon.service.download.DownloadActivity;
import com.demon.service.foreground.ForegroundActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Demon
 * @version 1.0
 * @Description : Service Demo
 * @DATE 2019/10/31 16:29
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.foregroundService, R.id.backgroundService, R.id.downloadService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 前台服务
            case R.id.foregroundService:
                ForegroundActivity.actionStart(MainActivity.this);
                break;
            // 后台服务
            case R.id.backgroundService:
                BackgroundActivity.actionStart(MainActivity.this);
                break;
            // 下载案例
            case R.id.downloadService:
                DownloadActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
