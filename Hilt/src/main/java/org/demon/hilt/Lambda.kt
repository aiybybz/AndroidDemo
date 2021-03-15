package org.demon.hilt

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/11
 */
class Lambda {

    fun a() {
        val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
        var maxLengthFruit = ""
        for (fruit in list) {
            if (fruit.length > maxLengthFruit.length) {
                maxLengthFruit = fruit
            }
        }
        println("max length fruit is $maxLengthFruit")
    }
}