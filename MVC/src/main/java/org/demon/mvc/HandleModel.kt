package org.demon.mvc

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import org.demon.mvc.farme.IModel
import org.demon.mvc.farme.IView

class HandleModel : IModel {

    private var view: IView? = null
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun setView(view: IView): HandleModel {
        this.view = view
        return this
    }

    /**
     * 接受到数据后，进行处理，这里设置了 3 秒的延迟，模拟网络请求处理数据的操作
     */
    override fun handleData(data: String) {
        if (TextUtils.isEmpty(data)) {
            return
        }
        view?.dataHanding()
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            // 数据处理完成，通知 View 更新界面
            view?.onDataHandled("handled data: $data")
        }, 3000)
    }

    /**
     * 接收到清空数据的事件，直接清空数据
     */
    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        // 数据清空后，通知 View 更新界面
        view?.onDataHandled("")
    }
}