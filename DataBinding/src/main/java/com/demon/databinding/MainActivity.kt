package com.demon.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    // 使用委托 懒加载 获取binding
    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        // 管理生命周期，可感知 Activity 生命周期，保证数据可见时才会更新
        binding.lifecycleOwner = this
    }
}
