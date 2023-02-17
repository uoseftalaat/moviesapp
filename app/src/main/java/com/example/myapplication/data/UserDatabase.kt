package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 6 , exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userdao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase (context: Context) : UserDatabase {
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "users"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}