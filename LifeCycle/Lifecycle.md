#### 一、Lifecycle

##### 1. 作用

• 简单的说就是用来监听Activity与Fragment的生命周期变化
• 通过`观察者模式` + `注解`来更方便的监听 `Activity` 和 `Fragment` 的生命周期变化

##### 2. 角色

##### 2.1. LifecycleOwner

生命周期拥有者
• `Livecycleowner` 用于连接有生命周期的对象，如 `activity`, `fragment`
• `AppCompatActivity` 与 `Fragment` 已默认实现了 `LifecyclerOwner` 接口
  • 即 `Activity` 与 `Fragment`（也可自定义，但局限性大）

##### 2.2. LifecycleObserver

生命周期观察者 
• 用于观察 `LifecycleOwner`
• 可以是任何类，常见的有`mvp`的`p`，自定义`View`等

##### 2.3. Lifecycle

• `Lifecycle` 可以有效的避免内存泄漏和解决android生命周期的常见难题
• `Livecycle` 是一个表示 android 生命周期及状态的对象
• `Livecycle` 框架使用 **观察者模式** 实现观察者监听被观察者的生命周期的变化

##### 3. 重要概念

• [`Lifecycle`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle?authuser=19&hl=th) 类，用于存储有关组件 (如 activity 或 fragment) 的生命周期的状态信息，允许其他对象观察此状态
• [`Lifecycle`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle?authuser=19&hl=th) 使用 **两种主要枚举** 跟踪其 **关联组件** 的 **生命周期** 状态

##### 3.1. 事件

• 从框架和 [`Lifecycle`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle?authuser=19&hl=th) 类分派的生命周期事件。这些事件映射到 activity 和 fragment 中的回调事件

##### 3.2. 状态

• 由 [`Lifecycle`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle?authuser=19&hl=th) 对象跟踪的组件的当前状态

##### 3.3. 关系图

 • 可以将状态看作图中的节点，将事件看作这些节点之间的边。 

![ Android activity 生命周期的状态和事件 ](https://developer.android.google.cn/static/images/topic/libraries/architecture/lifecycle-states.svg?authuser=19&hl=th)

##### 4. 使用

##### 4.1. 导包

```groovy
dependencies {
	...
    // ======================= Lifecycle =======================
    def lifecycle_version = "2.6.1"

    // Lifecycle only (without ViewModel or LiveData)
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    // Saved state module for ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
}
```

##### 4.2. 创建 LifecycleObserver

```kotlin
// 官方推荐 DefaultLifecycleObserver, owner可以获取环境
internal class MyLifecycleObserver : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(TAG, "MyLifecycleObserver - onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(TAG, "MyLifecycleObserver - onPause")
    }

    companion object {
        const val TAG = "MainLifecycle"
    }
}
```

##### 4.3. 建立联系

```kotlin
class MainObserverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 一般在 BaseActivity 关联注册
        lifecycle.addObserver(MyLifecycleObserver())
    }
}
```

##### 5. MVP P层设计

```kotlin
// 接口监听，设计模式的设计环节
class MVPActivity : AppCompatActivity() {

    // 用户端面向接口
    lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 一般在 BaseActivity 关联注册
        // 如果想动态变化则使用设计模式（工厂设计模式）
        presenter = MyPresenter()
        lifecycle.addObserver(presenter)
    }
}
```

```kotlin
// 接口隔离层
interface IPresenter : DefaultLifecycleObserver {}
```

```kotlin
// 接口实现类，可有多个
class MyPresenter : IPresenter {

    override fun onResume(owner: LifecycleOwner) {
        Log.i(TAG, "MyPresenter - onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i(TAG, "MyPresenter - onPause")
    }

    companion object{
        const val TAG = "MyLifecycle"
    }
}
```

##### 6. 其他

##### 6.1. 处在的生命周期

• 判断当前处在的生命周期

```kotlin
// 不是用来判断是否处于某个状态，而且用来判断是否到达了某个状态
if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
	// do something After Started
}
```

##### 6.2. 自定义 LifecycleOwner

• 注册 : `lifecycle = LifecycleRegistry()`
• 指定标记 : `lifecycle.currentState = Lifecycle.State.XXX`

```kotlin
// 自定义 LifecycleOwner
class CusOwnerActivity : Activity(), LifecycleOwner {

    override lateinit var lifecycle: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 1.注册
        lifecycle = LifecycleRegistry(this@CusOwnerActivity)
        // 2.指定为 CREATED 标记
        lifecycle.currentState = Lifecycle.State.CREATED
    }

    override fun onStart() {
        super.onStart()
        // 2.指定为 STARTED 标记
        lifecycle.currentState = Lifecycle.State.STARTED
    }
}
```



#### 源码分析

• 大部分系统源码，都会做 map 缓存，尤其 Framework 层源码，大量都做 map 缓存
• 因为涉及到反射，所以使用 map 来进行缓存，可以提高一点点性能
• **看到 `Framework`  源码总结的经验** : 只要涉及到反射就会使用 map 进行缓存，用来提高一点点性能
  •  第一次创建，第二个直接从缓存里面取 (反射) 