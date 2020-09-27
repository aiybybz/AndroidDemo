package org.demon.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/9/27
 */
@HiltAndroidApp
class Application : Application() {

    companion object {
        var instance: Application by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}