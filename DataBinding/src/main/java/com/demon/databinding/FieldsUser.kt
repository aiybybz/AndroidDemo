package com.demon.databinding

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 单个变量的绑定：fields
 * @date : 2020/9/5
 */
data class FieldsUser(
    val name: ObservableField<String>,
    val age: ObservableInt
)