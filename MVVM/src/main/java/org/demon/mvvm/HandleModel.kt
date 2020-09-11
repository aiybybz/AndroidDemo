package org.demon.mvvm

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import org.demon.mvvm.frame.IModel
import org.demon.mvvm.frame.IViewModel

/**
 * @author : Demon
 * @version : 1.0
 * @Description : HandleModel
 * @date : 2020/9/11
 */
class HandleModel : IModel {

    private var viewModel: IViewModel? = null
    private var handler = Handler(Looper.getMainLooper())


    override fun handleData(data: String?) {
        if (TextUtils.isEmpty(data)) {
            return
        }
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            // 数据处理完成通知 ViewModel
            viewModel?.dataHandled("handled data: $data")
        }, 3000)
    }

    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        // 数据清理完成通知 ViewModel
        viewModel?.dataCleared()
    }

    override fun setViewModel(viewModel: IViewModel) {
        this.viewModel = viewModel
    }
}