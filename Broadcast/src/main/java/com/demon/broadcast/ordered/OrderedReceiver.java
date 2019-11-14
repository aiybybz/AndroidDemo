package com.demon.broadcast.ordered;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 有序广播 Demo
 * @date : 2019/11/14
 */
public class OrderedReceiver {

    /**
     * 有序广播接收器 1
     */
    public static class OrderedOneReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了（1）有序广播！", Toast.LENGTH_SHORT).show();
            // 传递结果到下一个广播接收器
            int code = 0;
            String data = "hello";
            setResult(code, data, null);
        }
    }

    /**
     * 有序广播接收器 2
     */
    public static class OrderedTwoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取上一个广播接收器结果
            int code = getResultCode();
            String data = getResultData();
            Toast.makeText(context, "接收到了（2）有序广播！" + "\nCODE : " + code + "\t DATA : " + data, Toast.LENGTH_SHORT).show();
            //拦截广播
            abortBroadcast();
        }
    }

    /**
     * 有序广播接收器 3
     */
    public static class OrderedThreeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了（3）有序广播！", Toast.LENGTH_SHORT).show();
        }
    }

}
