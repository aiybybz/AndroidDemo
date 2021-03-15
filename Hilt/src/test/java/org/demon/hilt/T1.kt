package org.demon.hilt

import org.junit.Test

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2021/2/22
 */
class T1 {

    inline fun printString(str: String, block: (String) -> Unit) {
        println("printString begin")
        block(str)
        println("printString end")
    }

    @Test
    fun main() {
        println("main start")
        val str = ""
        printString(str) { s ->
            println("lambda start")
            if (s.isEmpty()) return
            println(s)
            println("lambda end")
        }
        println("main end")
    }

}

inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
}
