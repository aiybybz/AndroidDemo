package org.demon.mvvm_jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/9/16
 * <p> 使用 Dagger  </p>
 */
@Singleton
class UserRepository @Inject constructor(
    private val webservice: Webservice,
    // Simple in-memory cache. Details omitted for brevity.
    private val userCache: UserCache
) {
    fun getUser(userId: String): LiveData<User> {
        val cached: LiveData<User> = userCache.get(userId)
        if (cached != null) {
            return cached
        }
        val data = MutableLiveData<User>()
        // The LiveData object is currently empty, but it's okay to add it to the
        // cache here because it will pick up the correct data once the query
        // completes.
        userCache.put(userId, data)
        // This implementation is still suboptimal but better than before.
        // A complete implementation also handles error cases.
        webservice.getUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                data.value = response.body()
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO()
            }
        })
        return data
    }

}