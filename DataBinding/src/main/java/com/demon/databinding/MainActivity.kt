package com.demon.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap

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
        // 绑定数据
        binding.data = "Simple"
        binding.user = User("Demon", 18)

        // 绑定可观察的 Map
        binding.map = ObservableArrayMap<String, Any>().apply { put("key1", 888) }
        // 绑定可观察的 List
        binding.list = ObservableArrayList<Any>().apply { add(999) }

    }
}
