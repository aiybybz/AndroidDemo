package com.demon.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap

class MainActivity : AppCompatActivity() {

    // 使用委托 懒加载 获取binding
    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main) }
    private var input = "SSS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initBinding()
        initListener()
    }

    private fun initBinding() {
        // 管理生命周期，可感知 Activity 生命周期，保证数据可见时才会更新
        binding.lifecycleOwner = this

        // 绑定数据
        binding.data = "https://upload-images.jianshu.io/upload_images/3087349-1e1d11b37e009b69.png"
        binding.user = User("Demon", 18)

        // 绑定可观察的 Map
        binding.map = ObservableArrayMap<String, Any>().apply { put("key1", 888) }
        // 绑定可观察的 List
        binding.list = ObservableArrayList<Any>().apply { add(999) }
    }

    private fun initListener() {
        // 绑定监听
        binding.handler = EventHandler()
    }

    inner class EventHandler{
        fun onClick(v :View){
            Toast.makeText(v.context, "Click", Toast.LENGTH_SHORT).show()
        }
    }
}
