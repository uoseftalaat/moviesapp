package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun adduser(user: User)

    @Query("SELECT password from users where email like :user")
    fun getpassword(user:String): String

    @Query("SELECT password from users where email LIKE :email")
    fun if_exists(email:String) : String
}