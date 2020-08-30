#### 一、Lifecycle

##### 1. 作用

• 简单的说就是用来监听Activity与Fragment的生命周期变化
• 通过`观察者模式` + `注解`来更方便的监听 `Activity` 和 `Fragment` 的生命周期变化

##### 2. 角色

• **LifecycleOwner** : 生命周期拥有者
  • 即 `Activity` 与 `Fragment`（也可自定义，但局限性大）

• **LifecycleObserver** : 生命周期观察者
  • 可以是任何类，常见的有`mvp`的`p`，自定义`View`等

##### 3. 使用

##### 3.1. LifecycleOwner

• `AppCompatActivity` 与 `Fragment` 已默认实现了 `LifeCyclerOwner` 接口
• `LifeCyclerOwner` 接口
• `RetentionPolicy.RUNTIME` 运行时注解，在运行时通过反射去识别的注解

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public interface LifecycleOwner {
   
    Lifecycle getLifecycle();
}
```

##### 3.2. LifecycleObserver

• 生命周期观察者实现 `LifeCycleObserver` 接口，并使用注解 `OnLifecycleEvent`
• 下例是对 `LifeCycleOwner` 的 `resume`、`pause` 进行观察，在 `LifeCycleOwner` 生命周期产生变化的时候会调用 `LifeCycleObserver` 中注解修饰的方法

```kotlin
const val TAG = "MyLifeCycleObserver"

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 生命周期观察者
 * @Date : 2020/8/30
 */
internal class MyLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onActivityResume() {
        Log.i(TAG, "ActivityResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onActivityPause() {
        Log.i(TAG, "ActivityPause")
    }
}
```

##### 3.3. 建立联系

• 由于 `AppCompatActivity` 已实现 `LifeCycleOwner` 接口，只需 `lifecycle.addObserver()`

```kotlin
/**
 * @author : Demon
 * @version : 1.0
 * @Description : 生命周期拥有者
 * @Date : 2020/8/30
 */
class MainObserverActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        // 添加生命周期观察者
        lifecycle.addObserver(MyLifecycleObserver())
    }
}
```

##### 4. 其他

##### 4.1. 处在的生命周期

• 判断当前处在的生命周期

```kotlin
// 不是用来判断是否处于某个状态，而且用来判断是否到达了某个状态
if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
	// do something After Started
}
```

##### 4.2. 自定义 LifecycleOwner

• 注册 : `lifecycle = LifecycleRegistry()`
• 指定标记 : `lifecycle.currentState = Lifecycle.State.XXX`

```kotlin
/**
 * @author : Demon
 * @version : 1.0
 * @Description : 自定义 LifecycleOwner
 * @data : 2020/8/30
 */
class CusOwnerActivity : Activity(), LifecycleOwner {

    private lateinit var lifecycle: LifecycleRegistry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
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

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

}
```