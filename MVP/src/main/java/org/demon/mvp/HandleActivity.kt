package org.demon.mvp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import org.demon.mvp.databinding.ActivityHandleBinding
import org.demon.mvp.frame.IPresenter
import org.demon.mvp.frame.IView

class HandleActivity : AppCompatActivity(), IView {

    var mPresenter: IPresenter? = null

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityHandleBinding>(this, R.layout.activity_handle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initMVP()
        binding.lifecycleOwner = this

        binding.title = "MVP-框架"
        binding.handler = EventHandler()
    }

    private fun initMVP() {
        val presenter = HandPresenter()
        val model = HandleModel().setPresenter(presenter)
        presenter.setModel(model).setView(this)
        setPresenter(presenter)
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

    inner class EventHandler {
        fun onClick(v: View) {
            when (v.id) {
                // 通知 Controller 数据产生变化
                R.id.btn_1 ->
                    // 传递 文字修改 事件给 Presenter
                    mPresenter?.onTextChanged("数据变化")
                // 通知 Controller 清空数据事件
                R.id.btn_2 ->
                    // 传递按钮点击事件给 Presenter
                    mPresenter?.onClearBtnClicked()
            }
        }
    }

    override fun setPresenter(presenter: IPresenter) {
        this.mPresenter = presenter
    }

    // 展示数据处理中的视图
    override fun loading() {
        binding.title = "handling data ..."
    }

    override fun showData(data: String) {
        binding.title = data
    }
}
