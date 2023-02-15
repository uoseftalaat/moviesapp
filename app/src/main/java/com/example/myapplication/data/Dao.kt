package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun adduser(user: User)

    @Query("SELECT password from users where email LIKE :useremail")
    fun getpassword(useremail:String): String

    @Query("SELECT password from users where email LIKE :useremail")
    fun if_exists(useremail:String) : String
}