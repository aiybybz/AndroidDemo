package org.demon.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/9/27
 */
@HiltAndroidApp
class Application : Application() {

    // 告诉 Hilt 如何提供该类的实例
    @Inject
    lateinit var hiltSimple : HiltSimple

    companion object {
        var instance: Application by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        hiltSimple.doSomething()
    }
}