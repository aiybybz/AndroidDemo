package com.demon.service.background;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.demon.service.R;
import com.orhanobut.logger.Logger;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Demon
 * @version 1.0
 * @Description 后台服务调用
 * @DATE 2019/10/31 16
 */
public class BackgroundActivity extends AppCompatActivity {

    private BackgroundService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        // 活动与服务成功绑定
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.d("ServiceConnection onServiceConnected");
            downloadBinder = (BackgroundService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.gerProgress();
        }

        // 活动与服务解绑
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d("ServiceConnection onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.startBtn, R.id.stopBtn, R.id.bindService, R.id.unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 开始服务
            case R.id.startBtn:
                Intent startIntent = new Intent(this, BackgroundService.class);
                startService(startIntent);
                break;
            // 停止服务
            case R.id.stopBtn:
                Intent stopIntent = new Intent(this, BackgroundService.class);
                stopService(stopIntent);
                break;
            // 绑定服务
            case R.id.bindService:
                Intent bindIntent = new Intent(this, BackgroundService.class);
                // 绑定服务，BIND_AUTO_CREATE：活动和服务进行绑定后自动创建服务
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            // 解绑服务
            case R.id.unbindService:
                unbindService(connection);
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BackgroundActivity.class);
        context.startActivity(intent);
    }
}
