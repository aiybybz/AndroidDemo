package com.demon.databinding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private var input = "SSS"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 获取 binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 将 LiveData 赋值
        val desc = MutableLiveData<String>()
        // 绑定数据
        binding.desc = desc

        // 双向绑定
        binding.input = input
        initListener()
    }

    private fun initListener() {
        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("===MainFragment===", input)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}
