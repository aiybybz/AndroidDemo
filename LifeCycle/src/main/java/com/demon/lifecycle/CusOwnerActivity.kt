package com.demon.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

// 自定义 LifecycleOwner
class CusOwnerActivity : Activity(), LifecycleOwner {

    override lateinit var lifecycle: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        // 1.注册
        lifecycle = LifecycleRegistry(this@CusOwnerActivity)
        // 2.指定为 CREATED 标记
        lifecycle.currentState = Lifecycle.State.CREATED
    }

    override fun onStart() {
        super.onStart()
        // 2.指定为 STARTED 标记
        lifecycle.currentState = Lifecycle.State.STARTED
    }
}