package com.example.myapplication.data

import androidx.lifecycle.LiveData

class MovieRepo(private val moviedao: movieDao) {

    suspend fun getmovie():LiveData<List<Movies>> = moviedao.getmovies()

    suspend fun addmovie(movie:Movies){
        moviedao.addmovie(movie)
    }
}