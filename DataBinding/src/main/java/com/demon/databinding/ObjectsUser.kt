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
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
}