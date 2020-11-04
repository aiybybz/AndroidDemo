package org.demon.mvvm_jetpack.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/4
 */
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
