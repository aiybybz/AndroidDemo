package org.demon.mvc.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 控制器接口，定义控制器的逻辑
 * @date : 2020/9/8
 */
interface IController {

    /**
     * 设置 数据模型
     * @param model Model
     */
    fun setModel(model: IModel): IController

    /**
     * 数据改变
     * @param data 数据
     */
    fun onDataChanged(data: String)

    /**
     * 清空按钮点击事件
     */
    fun clearData()


    //====================== 说明 ======================
    // 根据数据流程 Controller 需要持有 Model 所以需要暴露相应的接口
    //
    //=================================================
}