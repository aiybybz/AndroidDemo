package org.demon.camera

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")

    @Test
    fun nWith() {
        // =========== 未使用 with ===========
        val builder = StringBuilder()
        builder.append("Start eating fruits.\n")
        for (fruit in list) {
            builder.append(fruit + "\t")
        }
        builder.append("\nAte all fruits")
        println(builder.toString())
    }

    @Test
    fun with() {
        // =========== 使用 with ===========
        val result = with(StringBuilder()) {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit + "\t")
            }
            append("\nAte all fruits")
            toString()
        }
        println(result)
    }

    @Test
    fun run() {
//        val result = obj.run{
//            // obj：上下文
//            "value" // run 函数的返回值
//        }

        val result = StringBuilder().run {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit + "\t")
            }
            append("\nAte all fruits")
            toString()
        }
        println(result)
    }

    @Test
    fun apply() {
//        val result = obj.apply{
//            // obj：上下文
//        }
//        // result = obj

        val result = StringBuilder().apply {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit + "\t")
            }
            append("\nAte all fruits")
        }
        println(result.toString())
    }

}