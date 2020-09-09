package org.demon.mvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.demon.mvc.databinding.ActivityHandleBinding
import org.demon.mvc.farme.IController
import org.demon.mvc.farme.IModel
import org.demon.mvc.farme.IView

class HandleActivity : AppCompatActivity(), IView {

    private var controller: IController = HandleController()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityHandleBinding>(this, R.layout.activity_handle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.lifecycleOwner = this

        binding.title = "MVC-框架"
        binding.handler = EventHandler()

        setController(controller)
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


    override fun setController(controller: IController) {
        this.controller = controller
    }

    override fun dataHanding() {
        binding.title = "数据改变了ing"
    }

    override fun onDataHandled(data: String) {
        binding.title = "handle data ..."
        controller.onDataChanged("handle data ...")
    }


    inner class EventHandler {
        fun onClick(v: View) {
            when (v.id) {
                // 通知 Controller 数据产生变化
                R.id.btn_1 -> controller.onDataChanged("数据变化")
                // 通知 Controller 清空数据事件
                R.id.btn_2 -> controller.clearData()
            }
        }
    }
}