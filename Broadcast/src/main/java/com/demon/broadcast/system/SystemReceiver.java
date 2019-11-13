package com.demon.broadcast.system;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 系统广播接收器
 * @date : 2019/11/12
 */
public class SystemReceiver extends BroadcastReceiver {

    // 蓝牙开关状态的广播
    // android.bluetooth.adapter.action.STATE_CHANGED

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);
        StringBuffer msg = new StringBuffer();
        msg.append("接收到了：");
        switch (state) {
            case BluetoothAdapter.STATE_OFF:
                msg.append("蓝牙关闭的广播");
                break;
            case BluetoothAdapter.STATE_ON:
                msg.append("蓝牙打开的广播");
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                msg.append("蓝牙正在关闭的广播");
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                msg.append("蓝牙正在打开的广播");
                break;
            default:
                break;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
