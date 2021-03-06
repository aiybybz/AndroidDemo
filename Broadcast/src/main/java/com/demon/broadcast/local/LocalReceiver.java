package com.demon.broadcast.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 本地广播接收器
 * @date : 2019/11/12 16:35:42
 */
public class LocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到了本地广播！", Toast.LENGTH_SHORT).show();
    }
}
