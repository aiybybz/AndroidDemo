package com.demon.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle

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
        init()
    }

    private fun init() {
        // 添加生命周期观察者
        lifecycle.addObserver(MyLifecycleObserver())

        // 判断是否到达了某个状态
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            // do something After Started
        }
    }

}
