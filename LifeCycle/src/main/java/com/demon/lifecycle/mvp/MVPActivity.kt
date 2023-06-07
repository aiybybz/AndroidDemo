package com.demon.lifecycle.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demon.lifecycle.R

/**
 * @Description : 接口监听，设计模式的设计环节
 */
class MVPActivity : AppCompatActivity() {

    // 用户端面向接口
    lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 一般在 BaseActivity 关联注册
        // 如果想动态变化则使用设计模式（工厂设计模式）
        presenter = MyPresenter()
        lifecycle.addObserver(presenter)
    }
}
