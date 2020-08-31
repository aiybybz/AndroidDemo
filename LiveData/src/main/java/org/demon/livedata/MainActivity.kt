package org.demon.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.demon.livedata.databinding.ActivityMainBinding
import java.math.BigDecimal

/**
 * @author : Demon
 * @version : 1.0
 * @Description : LiveData 使用
 * @date : 2020/8/31
 */
class MainActivity : AppCompatActivity() {

    // 1.创建 LiveData
    private val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    // 使用懒加载 DataBinding
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initLiveData()
        initListener()
    }

    private fun initLiveData() {
        // 2.创建 Observe
        val nameObserver = Observer<String> { newName ->
            binding.data = newName
        }
        // 3.Observer 添加到 LiveData
        currentName.observe(this@MainActivity, nameObserver)


//        StockLiveData.get(symbol).observe(viewLifecycleOwner, Observer<BigDecimal> { price: BigDecimal? ->
//            // Update the UI.
//        })
    }

    private fun initListener() {
        // 点击时改变 LiveData 值
        binding.button.setOnClickListener {
            // 4.UI 线程通过 value 修改 LiveData 值
            currentName.value = "2234"
        }

        // 点击时改变 LiveData 值
        binding.button.setOnClickListener {
            Thread {
                var num = 0
                while (num < 5) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    // 4.子线程通过 postValue() 修改 LiveData 值
                    currentName.postValue((++num).toString())
                }
            }.start()
        }
    }

}
