package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1 , exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): movieDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase (context: Context) : MovieDatabase {
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "users"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}