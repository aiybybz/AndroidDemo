package org.demon.mvvm.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : ViewModel 接口，定义了逻辑操作
 * @date : 2020/9/10
 */
interface IViewModel {
    /**
     *
     */
    fun setModel(model: IModel)
    fun handleText(text: String?)
    fun clearData()
    fun dataHandled(data: String?)
    fun dataCleared()
}