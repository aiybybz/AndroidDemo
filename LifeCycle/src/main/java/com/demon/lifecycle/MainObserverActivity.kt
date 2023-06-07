package com.demon.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 生命周期拥有者
 * @Date : 2020/8/30
 */
class MainObserverActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 一般在 BaseActivity 关联注册
        lifecycle.addObserver(MyLifecycleObserver())
    }

//        // 判断是否到达了某个状态
//        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//            // TODO do something After Started
//        }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "MainObserverActivity - onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "MainObserverActivity - onResume")
    }

    companion object {
        const val TAG = "MainLifecycle"
    }
}
