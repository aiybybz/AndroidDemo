package org.demon.mvp.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 视图接口，定义了视图的操作
 * @date : 2020/9/10
 */
interface IView {
    /**
     * 设置 控制器
     * @param presenter 控制器
     */
    fun setPresenter(presenter: IPresenter)

    /**
     * 数据处理中视图
     */
    fun loading()

    /**
     * 展示数据
     */
    fun showData(data: String)


    //====================== 说明 ======================
    // View 和 Model 会持有 Presenter
    //
    //=================================================
}