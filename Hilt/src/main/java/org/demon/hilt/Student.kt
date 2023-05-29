package org.demon.hilt

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/9
 */

open class User(name: String) {
    var name: String = name
}

class Student(val sno: String, name: String) : User(name) {
}

// Student name 参数不能 定义 val/var
// 因为主构造声明成 val/var 将自动成该类的字段，会导致父类中 name 字段冲突


class Student1(val sno: String, name: String) : User(name) {
    // 次级构造(1)：接收 name 参数，然后通过 this 调用主构造，并赋值初始化 sno
    constructor(name: String) : this("", name) {
    }

    // 次级构造(2)：不接收参数，通过 this 调用 次级构造(1)，并赋值初始化 sno
    constructor() : this("") {
    }
}

// 使用
val student1 = Student1()
val student2 = Student1("Jack")
val student3 = Student1("AK47", "Jack")

// 定义
data class Cellphone(val brand: String, val price: Double)

// 使用
fun main(){
    val cellphone1 = Cellphone("Mi",1299.99)
    val cellphone2 = Cellphone("Mi",1299.99)
    println("cellphone1 equals cellphone2" + (cellphone1 == cellphone2))
}