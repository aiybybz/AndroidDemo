package org.demon.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import java.math.BigDecimal

class StockLiveData(symbol: String) : LiveData<BigDecimal>() {

    private val stockManager = StockManager(symbol)

    // setValue(T) 方法将更新 LiveData 实例的值，并将更改通知给任何活跃观察者。
    private val listener = { price: BigDecimal ->
        value = price
    }

    // 当 LiveData 对象具有活跃观察者时，会调用 onActive() 方法。
    // 这意味着，您需要从此方法开始观察股价更新。
    override fun onActive() {
        stockManager.requestPriceUpdates(listener)
    }

    // 当 LiveData 对象没有任何活跃观察者时，会调用 onInactive() 方法。
    // 由于没有观察者在监听，因此没有理由与 StockManager 服务保持连接。
    override fun onInactive() {
        stockManager.removeUpdates(listener)
    }


    // 将 LiveData 类实现为单一实例
    // 可以在多个 Activity、Fragment 和 Service 之间共享这些对象
    companion object {
        private lateinit var sInstance: StockLiveData

        @MainThread
        fun get(symbol: String): StockLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else StockLiveData(symbol)
            return sInstance
        }
    }
}