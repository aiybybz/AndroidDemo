package org.demon.mvvm.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : ViewModel 接口，定义了逻辑操作
 * @date : 2020/9/10
 */
interface IViewModel {

    /**
     * 设置 Model
     * @param model [IModel]
     */
    fun setModel(model: IModel)

    /**
     * 处理文本
     * @param text 文本信息
     */
    fun handleText(text: String?)

    /**
     * 清除数据
     */
    fun clearData()

    /**
     * 数据处理完成
     * @param data 数据
     */
    fun dataHandled(data: String?)

    /**
     * 数据清理完成
     */
    fun dataCleared()
}