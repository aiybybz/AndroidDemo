package com.demon.broadcast.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 标准广播接收器 + 静态注册
 * @date : 2019/11/12 16:32
 */
public class NormalReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收到了标准广播！",Toast.LENGTH_SHORT).show();
    }
}
