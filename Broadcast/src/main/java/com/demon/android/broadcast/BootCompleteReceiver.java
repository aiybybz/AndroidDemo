package com.demon.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Demon
 * @version 1.0
 * @Description : 
 * @DATE 2019/11/8 17:32
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        不要在onReceive()方法中添加过多的逻辑或者进行任何的耗时操作，
//        因为在广播接收器中是不允许开启线程的，
//        当onReceive()方法运行了较长时间而没有结束时，程序就会报错。
//        因此广播接收器更多的是扮演一种打开程序其他组件的角色，比如创建一条状态栏通知，
//        或者启动一个服务等。

        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show();
    }
}
