package org.demon.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!::str.isInitialized) {
            str = "111"
        }
        Log.i("HH", str)
        cycle()
    }

    fun cycle() {
        repeat(2) {
            Log.i("HH", "it=$it")
        }
        // 输出结果
        //   it=0
        //   it=1
    }


}
