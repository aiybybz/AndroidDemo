package org.demon.mvvm_jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private val id: String,
    /**
     * 姓名
     */
    @ColumnInfo(name = "name")
    private val name: String,
    /**
     * 年龄
     */
    @ColumnInfo(name = "age")
    private val age: Int
)