package org.demon.mvp.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 控制器，定义了逻辑操作
 * @date : 2020/9/10
 */
interface IPresenter {

    /**
     * 设置 视图
     * @param view 视图
     */
    fun setView(view: IView): IPresenter

    /**
     * 设置 数据模型
     * @param model 数据模型
     */
    fun setModel(model: IModel): IPresenter

    /**
     * Model 处理完成数据通知 Presenter
     */
    fun dataHandled(data: String)

    /**
     * Model 清除数据后通知 Presenter
     */
    fun dataCleared()

    /**
     * View 中 EditText 文字变化后通知 Presenter
     */
    fun onTextChanged(text: String)

    /**
     * View 中 Button 点击事件通知 Presenter
     */
    fun onClearBtnClicked()


    //====================== 说明 ======================
    // Presenter 持有 View 和 Model
    //
    //=================================================
}