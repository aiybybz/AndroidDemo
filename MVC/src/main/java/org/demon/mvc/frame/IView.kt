package org.demon.mvc.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 视图接口，定义视图的操作
 * @date : 2020/9/8
 */
interface IView {

    /**
     * 设置控制器
     *
     */
    fun setController(controller: IController)

    /**
     * 数据处理中状态
     */
    fun dataHanding()

    /**
     * 数据处理完成，更新界面
     * @param data 数据处理完消息
     */
    fun onDataHandled(data: String)


    //====================== 说明 ======================
    // 根据数据流程 View 需要持有 Controller 所以需要暴露相应的接口
    //
    //=================================================
}