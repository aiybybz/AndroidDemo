package org.demon.mvc.farme

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 数据模型接口，定义数据模型的操作
 * @date : 2020/9/8
 */
interface IModel {

    /**
     * 设置 视图
     * @param view View
     */
    fun setView(view: IView)

    /**
     * 数据模型处理输入的数据
     * @param data 数据
     */
    fun handleData(data: String)

    /**
     * 清空数据
     */
    fun clearData()


    //====================== 说明 ======================
    // 根据数据流程 Model 需要持有 View 所以需要暴露相应的接口
    //
    //=================================================
}