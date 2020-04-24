## AIDL - 简介

### 一、概述

AIDL ：Android Interface Definition Language，即 **Android接口定义语言**。

- 用于定义服务器和客户端通信接口的一种**描述语言**，可以拿来生成用于 IPC 的代码。

IPC ：Inter-Process Communication，即**跨进程通信**。

### 二、基础

#### 1.文件

- AIDL文件以 `.aidl` 为后缀名。
- AIDL文件分为两类：
  - 一类用来声明实现了Parcelable接口的数据类型，以供其他AIDL文件使用那些非默认支持的数据类型。（注：文件内可注释）
  - 另一类用来定义接口方法，声明要暴露哪些接口给客户端调用，定向Tag就是用来标注这些方法的参数值。（注：文件内不可注释）

#### 2.数据类型

- byte、char、short、int、long、float、double、boolean、还有String、CharSequence。
- 实现了 Parcelable 接口的数据类型。
- List 类型。
  - List承载的数据必须是AIDL支持的类型，或者是其它声明的AIDL对象。
- Map类型。
  - Map承载的数据必须是AIDL支持的类型，或者是其它声明的AIDL对象。

#### 3.定向Tag

定向Tag，在跨进程通信中表示数据的流向，用于标注方法的参数值。

- in：
  - 数据只能由客户端传向服务端。
  - 服务端对数据的修改不会影响到客户端。
- out：
  - 数据只能由服务端传向客户端。
  - 即使客户端向方法接口传入了一个对象，该对象中的属性值也是为空的，即不包含任何数据，服务端获取到该对象后，对该对象的任何操作，就会同步到客户端这边。
- inout：
  - 数据可在服务端与客户端之间双向流通。
  - 服务端对数据的改变同时也同步到了客户端。

### 三、注意

- 如果AIDL方法接口的参数值类型是：基本数据类型、String、CharSequence或者其他AIDL文件定义的方法接口，那么这些参数值的定向 Tag 默认是且只能是 in。以外的参数值都需要明确标注使用哪种定向Tag。

- 明确导包。在AIDL文件中需要明确标明引用到的数据类型所在的包名。
- 避免出现类名重复导致无法创建文件的错误，需要先建立AIDL文件之后再创建类。
  - 例：先创建 `User.aidl`，再创建 `User.java`

- 实现 Parcelable 的类需要补充 `readFromParcel(Parcel dest)` 不然编译不过。

  - ```java
    // 例（userName password mark 为变量）
    public void readFromParcel(Parcel dest) {
        userName = dest.readString();
        password = dest.readString();
        mark = dest.readByte() != 0;
    }
    ```

- 定义接口方法的 AIDL 文件是不能注释的（否则无法编译）。

- 写完 接口定义需 Make Module。

- 在进程间通信中真正起作用的并不是 `AIDL` 文件，而是系统据此而生成的文件，可以在以下目录中查看系统生成的文件。之后需要使用到当中的内部静态抽象类 `Stub` 。
- 调用 `AIDL` 的程序需要把 `AIDL` 文件及实现 `Parcelable` 接口的数据类拷贝过来（不要修改代码，不要修改包路径）。

## AIDL - 使用

[Demo](https://github.com/aiybybz/AndroidDemo)

- 服务端：AIDL-Service
- 客户端：AIDL-Client



