package com.demon.lifecycle.mvp

import android.util.Log
import androidx.lifecycle.LifecycleOwner


class MyPresenter : IPresenter {

    override fun onResume(owner: LifecycleOwner) {
        Log.i(TAG, "MyPresenter - onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i(TAG, "MyPresenter - onPause")
    }

    companion object{
        const val TAG = "MyLifecycle"
    }
}