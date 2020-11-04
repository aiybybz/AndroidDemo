package org.demon.mvvm_jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.demon.mvvm_jetpack.room.User
import org.demon.mvvm_jetpack.room.UserDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/9/16
 * <p> 使用 Hilt  </p>
 */
// Hilt 提供单例
@Singleton
class UserRepository @Inject constructor(
    private val webservice: Webservice,
    // 缓存数据
    private val executor: Executor,
    private val userDao: UserDao
) {

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

    fun getUser(userId: String): LiveData<User> {
        refreshUser(userId)
        return userDao.load(userId)
    }

    private fun refreshUser(userId: String) {
        // 运行在后台线程
        executor.execute {
            // 是否需要获取最新数据
            val userExists = false
//            val userExists = userDao.hasUser(FRESH_TIMEOUT)
            if (!userExists) {
                // 刷新数据
                val response = webservice.getUser(userId).execute()

                // 检验异常

                // 更新数据
                userDao.insert(response.body()!!)
            }
        }
    }

}