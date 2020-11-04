package org.demon.hilt

import android.content.ContentValues.TAG
import android.util.Log
import javax.inject.Inject

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/3
 */
// 告诉 Hilt 如何提供该类的实例
class HiltSimple @Inject constructor() {

    fun doSomething() {
        Log.e(TAG, "----doSomething----")
    }
}