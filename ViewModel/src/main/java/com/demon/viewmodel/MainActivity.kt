package com.demon.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        // 获取 MyViewModel
        val model: MyViewModel by viewModels()
        model.getUsers().observe(this@MainActivity, Observer<List<User>> { users ->
            // update UI
        })

        // 获取 MyViewModel 简化后
        val mModel: MyViewModel by viewModels()
        // 获取 LiveData 实例
        mModel.getUsers().observe(this@MainActivity, { users ->
            // update UI
        })
        // 当重建时已经进行了缓存
    }


}
