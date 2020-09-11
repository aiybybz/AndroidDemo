package org.demon.mvp

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import org.demon.mvp.frame.IModel
import org.demon.mvp.frame.IPresenter

class HandleModel : IModel {

    private var presenter: IPresenter? = null
    private var handler = Handler(Looper.getMainLooper())


    override fun handleData(data: String) {
        if (TextUtils.isEmpty(data)) {
            return
        }
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            // 数据处理完成，通知 Presenter
            presenter?.dataHandled("handled data: $data")
        }, 3000)
    }

    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        // 数据清理完成，通知 Presenter
        presenter?.dataCleared()
    }

    override fun setPresenter(presenter: IPresenter): IModel {
        this.presenter = presenter
        return this
    }
}