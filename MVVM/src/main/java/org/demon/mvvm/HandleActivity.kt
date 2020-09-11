package org.demon.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.demon.mvvm.databinding.ActivityHandleBinding

class HandleActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityHandleBinding>(this, R.layout.activity_handle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initMVVM()
    }

    private fun initMVVM() {
        binding.lifecycleOwner = this
        val viewModel = HandleViewModel()
        viewModel.setModel(HandleModel())
        binding.vm = viewModel
    }


    companion object {
        /**
         * 启动activity方法
         * @param context 上下文
         */
        fun actionStart(context: Context) {
            val intent = Intent(context, HandleActivity::class.java)
            context.startActivity(intent)
        }
    }

}
