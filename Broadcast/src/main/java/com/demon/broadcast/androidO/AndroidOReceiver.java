package com.demon.broadcast.androidO;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : Android O（8.0）广播接收器
 * @date : 2019/11/12 16:32
 */
public class AndroidOReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收到了 Android O 广播！",Toast.LENGTH_SHORT).show();
    }
}
