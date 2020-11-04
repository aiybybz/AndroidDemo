package org.demon.mvvm_jetpack

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.demon.mvvm_jetpack.room.User

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 准备数据以便在 UserProfileFragment 中查看并对用户互动做出响应的类
 * @date : 2020/11/4
 */
// SavedStateHandle 允许 ViewModel 访问相关 Fragment 或 Activity 的已保存状态和参数
class UserProfileViewModel @ViewModelInject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) : ViewModel() {

    val userId: String = savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
    val user: LiveData<User> = userRepository.getUser(userId)

}
