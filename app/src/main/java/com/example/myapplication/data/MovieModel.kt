package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieModel(application: Application): AndroidViewModel(application) {
    lateinit var repository:MovieRepo
    lateinit var getmovie:LiveData<List<Movies>>
    init {
        val movieDao:movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepo(movieDao)
       viewModelScope.launch {
           getmovie = repository.getmovie()
       }
    }

    fun addmovie(movie:Movies){
        viewModelScope.launch {
            repository.addmovie(movie)
        }
    }
}