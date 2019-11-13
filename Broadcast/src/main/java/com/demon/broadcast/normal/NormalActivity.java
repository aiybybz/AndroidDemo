package com.demon.broadcast.normal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.demon.broadcast.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 标准广播 Demo
 * @date : 2019/11/12 16:14
 */
public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendBroadcast)
    public void onViewClicked() {
        Intent intent = new Intent();
        // 对应 BroadcastReceiver 中 intentFilter 的 action
        intent.setAction("NORMAL_ACTION");
        // 发送广播
        sendBroadcast(intent);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NormalActivity.class);
        context.startActivity(intent);
    }
}
