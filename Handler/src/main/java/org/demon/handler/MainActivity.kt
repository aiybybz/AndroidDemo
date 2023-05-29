package org.demon.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var tvContent: TextView
    private val mHandler = MyHandler(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvContent = findViewById(R.id.tv_content)
        Thread{
            mHandler.sendMessageDelayed(Message.obtain().apply {
                what = WHAT
                obj = "MyHandler"
            }, 5000)
        }.start()
    }

    private class MyHandler(val wrActivity: WeakReference<MainActivity>) : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            wrActivity.get()?.run {
                when(msg.what){
                    WHAT -> tvContent.text = msg.obj as String
                }
            }
        }
    }

    companion object {
        const val WHAT = 100
    }
}