package com.demon.broadcast.local;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.demon.broadcast.R;
import com.demon.broadcast.androidO.AndroidOActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 本地广播 Demo
 * @date : 2019/11/12 16:14
 */
public class LocalActivity extends AppCompatActivity {

    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册应用内广播接收器
        // 1.实例化 IntentFilter & BroadcastReceiver
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();

        // 2.实例化 LocalBroadcastManager 的实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // 3.设置接收广播的类型
        intentFilter.addAction("LOCAL_RECEIVER");

        // 4.调用 LocalBroadcastManager 单一实例的 registerReceiver() 进行动态注册
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 5.取消注册应用内广播接收器
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    @OnClick(R.id.sendBroadcast)
    public void onViewClicked() {
        // 6.发送应用内广播
        Intent intent = new Intent();
        intent.setAction("LOCAL_RECEIVER");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LocalActivity.class);
        context.startActivity(intent);
    }

}
