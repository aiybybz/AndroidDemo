package org.demon.mvp.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 模型接口，定义了数据模型的操作
 * @date : 2020/9/10
 */
interface IModel {

    /**
     * 设置 控制器
     * @param presenter 逻辑控制器
     */
    fun setPresenter(presenter: IPresenter): IModel

    /**
     * 处理数据
     */
    fun handleData(data: String)

    /**
     * 清除数据
     */
    fun clearData()


    //====================== 说明 ======================
    // View 和 Model 会持有 Presenter
    //
    //=================================================
}