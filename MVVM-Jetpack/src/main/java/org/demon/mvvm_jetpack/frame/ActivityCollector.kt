package org.demon.mvvm_jetpack.frame

import android.app.Activity

/**
 * @author : Demon
 * @version : 1.0
 * @Description : Activity 管理类
 * @date : 2020/11/24
 */
object ActivityCollector {

    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        activities.forEach { a ->
            if (!a.isFinishing) {
                a.finish()
            }
        }
        activities.clear()
    }

}