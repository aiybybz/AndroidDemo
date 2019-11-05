package com.demon.service.foreground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;

import com.demon.service.MainActivity;
import com.demon.service.R;
import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * @author Demon
 * @version 1.0
 * @Description 前台服务 + 不可通信
 * @DATE 2019/10/31 14
 */
public class ForegroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 服务创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("ForegroundService onCreate ： 前台服务");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "ForegroundService")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        // 使后台服务转为前台服务
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        // 停止前台服务
        stopForeground(true);
        super.onDestroy();
    }
}
