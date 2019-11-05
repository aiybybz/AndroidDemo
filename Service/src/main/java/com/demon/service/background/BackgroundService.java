package com.demon.service.background;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.orhanobut.logger.Logger;

/**
 * @author Demon
 * @version 1.0
 * @Description 后台服务 + 可通信
 * @DATE 2019/10/31 16:59
 */
public class BackgroundService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Logger.d("startDownload executed");
        }

        public int gerProgress() {
            Logger.d("gerProgress executed");
            return 0;
        }
    }

    /**
     * 服务创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("BackGroundService onCreate");
    }

    /**
     * 绑定服务时调用
     * @param intent Intent
     * @return IBinder
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 服务每次启动时调用
     * @param intent  Intent
     * @param flags   flags
     * @param startId startId
     * @return 系统在杀死服务后应该如何继续运行。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            Logger.d("BackGroundService onStartCommand");
            stopSelf();
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("BackGroundService onDestroy");
    }
}
