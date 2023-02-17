package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface movieDao {

    @Insert
    fun addmovie(movie: Movies)

    @Query("SELECT * from Movies")
    fun getmovies(): List<Movies>

    @Delete
    fun deleteRow(movie: Movies)
}