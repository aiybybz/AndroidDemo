package org.demon.mvvm

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import org.demon.mvvm.frame.IModel
import org.demon.mvvm.frame.IViewModel

/**
 * @author : Demon
 * @version : 1.0
 * @Description : ViewModel
 * @date : 2020/9/11
 */
class HandleViewModel : IViewModel {

    private var model: IModel? = null

    // View 绑定的数据，inputText 和 handledText 更新后会自动通知 View 更新界面
    var inputText: MutableLiveData<String> = MutableLiveData()
    var handledText: MutableLiveData<String> = MutableLiveData()


    init {
        // 注册数据监听，数据改变后通知 Model 去处理数据
        inputText.observeForever {
            handleText(it)
        }
        handledText.value = "default msg"
    }

    override fun handleText(text: String?) {
        if (TextUtils.isEmpty(text)) {
            handledText.value = "default msg"
            return
        }
        handledText.value = "handle data ..."
        model?.handleData(text)
    }

    /**
     * 清空按钮的点击事件绑定
     */
    override fun clearData() {
        model?.clearData()
    }

    override fun setModel(model: IModel) {
        this.model = model
        model.setViewModel(this)
    }

    /**
     * Model 数据处理完成，设置 handledText 的值，自动更新到界面
     */
    override fun dataHandled(data: String?) {
        handledText.value = data
    }

    /**
     * Model 数据处理完成，设置 inputText 的值，自动更新到界面
     */
    override fun dataCleared() {
        inputText.value = ""
    }

}