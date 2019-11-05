package com.demon.service.foreground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demon.service.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Demon
 * @version 1.0
 * @Description 前台服务调用
 * @DATE 2019/10/31 16
 */
public class ForegroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.startBtn, R.id.stopBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 开始服务
            case R.id.startBtn:
                Intent startIntent = new Intent(this, ForegroundService.class);
                startService(startIntent);
                break;
            // 停止服务
            case R.id.stopBtn:
                Intent stopIntent = new Intent(this, ForegroundService.class);
                stopService(stopIntent);
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ForegroundActivity.class);
        context.startActivity(intent);
    }

}
