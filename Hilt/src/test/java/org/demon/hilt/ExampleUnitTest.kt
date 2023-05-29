package org.demon.hilt

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}


// 使用
fun main() {

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult , allResult is $allResult")

    // 变为大写模式
    val newList = list.map { it.toUpperCase() }
    for (fruit in newList) {
        println(fruit)
    }

    // ====================== 查询最长的水果名 ======================
    // 1.普通写法
    var maxLengthFruit1 = ""
    for (fruit in list) {
        if (fruit.length > maxLengthFruit1.length) {
            maxLengthFruit1 = fruit
        }
    }
    println("max length fruit is $maxLengthFruit1")

    // 2.Lambda写法
    val maxLengthFruit2 = list.maxByOrNull { it.length }
    println("max length fruit is $maxLengthFruit2")

    // ======================= Lambda 写法演变过程 =======================
    // 定义参数
    val list1 = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")

    // ======== 1.定义并使用 Lambda
    // 定义 Lambda 参数
    val lambda = { fruit: String -> fruit.length }
    // 使用 Lambda 参数
    var maxLength1 = list1.maxByOrNull(lambda)

    // ======== 2.进行简化1
    // 不定义 Lambda 参数
    var maxLength2 = list1.maxByOrNull({ fruit: String -> fruit.length })

    // ======== 3.进行简化2
    // Kotlin 规定，当 Lambda 参数是函数的最后一个参数时，可以将 Lambda表达式移到函数括号外
    var maxLength3 = list1.maxByOrNull() { fruit: String -> fruit.length }

    // ======== 4.进行简化3
    // 如果 Lambda 参数是函数唯一参数，可以将 `()` 省略
    var maxLength4 = list1.maxByOrNull { fruit: String -> fruit.length }

    // ======== 5.进行简化4
    // 根据 Kotlin 类推导机制 可以省去 String
    var maxLength5 = list1.maxByOrNull { fruit -> fruit.length }

    // ======== 6.进行简化5
    // 当 Lambda 的参数列表只有一个参数时不必声明，可用 it 代替
    var maxLength6 = list1.maxByOrNull { it.length }


    // ======== 使用
    // 传入第一个参数，第二个参数使用默认值
    printParams1(1)

    printParams2(str = "aaa")
    // 不区分前后
    printParams2(str = "aaa", num = 101)

}

// ======== 定义参数默认值
// str 参数默认值为 hello
fun printParams1(num: Int, str: String = "hello") {
    println("num is $num , str is $str")
}

// ======== 定义参数默认值
// str 参数默认值为 hello
fun printParams2(num: Int = 100, str: String) {
    println("num is $num , str is $str")
}

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

inline fun inlineTest(block1: () -> Unit, noinline block2: () -> Unit) {
}

fun main1() {

}