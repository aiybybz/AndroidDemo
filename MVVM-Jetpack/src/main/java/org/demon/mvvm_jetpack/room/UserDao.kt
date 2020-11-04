package org.demon.mvvm_jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE


/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/4
 */
@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM User WHERE id = :userId")
    fun load(userId: String): LiveData<User>

}