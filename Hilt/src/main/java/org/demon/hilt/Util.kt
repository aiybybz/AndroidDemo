package org.demon.hilt

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/17
 */
class Util {

    fun doAction1() {
        println("do action1")
    }

    companion object {
        // 注解 真正的静态方法
        @JvmStatic
        fun doAction2() {
            println("do action2")
        }
    }
}