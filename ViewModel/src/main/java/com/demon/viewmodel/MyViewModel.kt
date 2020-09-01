package com.demon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val users: MutableLiveData<List<UserBean>> by lazy {
        MutableLiveData<List<UserBean>>()
    }

//    private val users: MutableLiveData<List<UserBean>> by lazy {
////        MutableLiveData().also {
////            loadUsers()
////        }
//    }

    fun getUsers(): LiveData<List<UserBean>> {
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}