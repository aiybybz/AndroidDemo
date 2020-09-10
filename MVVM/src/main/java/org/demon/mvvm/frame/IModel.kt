package org.demon.mvvm.frame

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 模型接口，定义了数据操作
 * @date : 2020/9/10
 */
interface IModel {
    /**
     * 设置 ViewModel
     * @param viewModel {@link IViewModel}
     */
    fun setViewModel(viewModel: IViewModel)
    fun handleData(data: String?)
    fun clearData()
}