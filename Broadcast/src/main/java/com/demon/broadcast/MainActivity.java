package com.demon.broadcast;

import android.os.Bundle;
import android.view.View;

import com.demon.broadcast.normal.NormalActivity;

import androidx.appcompat.app.AppCompatActivity;
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

    @OnClick({R.id.normalBroadcast, R.id.systemBroadcast, R.id.orderedBroadcast, R.id.localBroadcast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 标准广播
            case R.id.normalBroadcast:
                NormalActivity.actionStart(MainActivity.this);
                break;
            // 系统广播
            case R.id.systemBroadcast:

                break;
            // 有序广播
            case R.id.orderedBroadcast:

                break;
            // 本地广播
            case R.id.localBroadcast:

                break;
            default:
                break;
        }
    }
}
