package com.demon.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 生命周期观察者
 * @Date : 2020/8/30
 */
internal class MyLifecycleObserver : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(TAG, "MyLifecycleObserver - onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(TAG, "MyLifecycleObserver - onPause")
    }

    companion object {
        const val TAG = "MainLifecycle"
    }
}
