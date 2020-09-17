package org.demon.mvvm_jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// SavedStateHandle 允许 ViewModel 访问相关 Fragment 或 Activity 的已保存状态和参数。
class UserProfileViewModel(savedStateHandle: SavedStateHandle, userRepository: UserRepository) : ViewModel() {

    val userId: String = savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
    val user : LiveData<User> = userRepository.getUser(userId)

}
