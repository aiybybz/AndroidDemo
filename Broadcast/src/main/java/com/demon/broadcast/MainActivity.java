package com.demon.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.demon.broadcast.androidO.AndroidOActivity;
import com.demon.broadcast.local.LocalActivity;
import com.demon.broadcast.local.LocalReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : BroadcastReceiver Demo
 * @date : 2019/11/12 16:14
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.normalBroadcast, R.id.orderedBroadcast, R.id.localBroadcast, R.id.androidOBroadcast})
    public void onViewClicked(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            // 标准广播
            case R.id.normalBroadcast:
                // 对应 BroadcastReceiver 中 intentFilter 的 action
                intent.setAction("NORMAL_ACTION");
                // 发送广播
                sendBroadcast(intent);
                break;
            // 有序广播
            case R.id.orderedBroadcast:
                // 对应 BroadcastReceiver 中 intentFilter 的 action
                intent.setAction("ORDERED_RECEIVER");
                // 发送有序广播
                sendOrderedBroadcast(intent, null);
                break;
            // 本地广播
            case R.id.localBroadcast:
                LocalActivity.actionStart(MainActivity.this);
                break;
            // Android O（8.0）
            case R.id.androidOBroadcast:
                AndroidOActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
