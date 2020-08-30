package com.demon.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

const val TAG = "MyLifeCycleObserver"

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 生命周期观察者
 * @Date : 2020/8/30
 */
internal class MyLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onActivityResume() {
        Log.i(TAG, "ActivityResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onActivityPause() {
        Log.i(TAG, "ActivityPause")
    }
}
