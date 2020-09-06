package com.demon.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 对象的绑定：objects
 * @date : 2020/9/5
 */
class ObjectsUser : BaseObservable() {
    // 添加 @get:Bindable 注解变量
    @get:Bindable
    var name: String = ""
        // 重写 set
        set(value) {
            field = value
            // 调用 notifyPropertyChanged 更新该数据
            // BR 自动生成的，包名跟当前包名一致，会根据 Bindable 注解的变量生成对应的值
            notifyPropertyChanged(BR.name)
            // 更新所有数据
            // notifyChange()
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
}