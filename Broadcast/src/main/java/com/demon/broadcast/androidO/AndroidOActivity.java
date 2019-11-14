package com.demon.broadcast.androidO;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.demon.broadcast.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : Android O（8.0）广播修改 Demo
 * @date : 2019/11/12 16:14
 */
public class AndroidOActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_o);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendBroadcast)
    public void onViewClicked() {
//        sendBroadcastImplicit();
        sendBroadcastExplicit();
    }

    /**
     * 以隐式发送 静态注册的广播
     * 隐式广播：Android8.0 targetSDK >= 26，禁止了所有的隐式广播的静态注册监听。
     */
    private void sendBroadcastImplicit() {
        Intent intent = new Intent();
        // 对应 BroadcastReceiver 中 intentFilter 的 action
        intent.setAction("ANDROID_O");
        // 发送广播
        sendBroadcast(intent);
    }

    /**
     * 以显示发送 静态注册的广播
     * 显式广播：Android8.0 不限制
     */
    private void sendBroadcastExplicit() {
        sendLocalBroadcast();
//        sendAllBroadcast();
    }

    /**
     * 发送本地广播
     */
    private void sendLocalBroadcast(){
        Intent intent = new Intent();
        // 指定Action
        intent.setAction("ANDROID_O");
        // 指定包名
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    /**
     * 发送所有APP（其他 + 本地）
     * 说明：当想发给其他 App，而不知其包名时可通过pm把所有隐式注册了这个自定义广播的 App列出来，然后转成显式发送。
     */
    private void sendAllBroadcast() {
        Intent intent = new Intent();
        // 指定Action
        intent.setAction("ANDROID_O");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> matches = pm.queryBroadcastReceivers(intent, 0);
        for (ResolveInfo resolveInfo : matches) {
            intent.setPackage(resolveInfo.activityInfo.applicationInfo.packageName);
            sendBroadcast(intent);
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AndroidOActivity.class);
        context.startActivity(intent);
    }
}
