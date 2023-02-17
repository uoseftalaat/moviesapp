package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import kotlinx.coroutines.launch

class MovieModel(application: Application): AndroidViewModel(application) {
    lateinit var repository:MovieRepo
    lateinit var getmovie:List<Movies>
    lateinit var movie:Movies
    init {
        val movieDao:movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepo(movieDao)
       viewModelScope.launch {
           getmovie = repository.getmovie()
       }
    }
    var movies = getmovie

    public fun addmovie(movie:Movies){
        viewModelScope.launch {
            repository.addmovie(movie)
        }
    }
    fun delete(movie: Movies){
        viewModelScope.launch {
            repository.delete(movie)
        }
    }
    fun checkNull(email:String , password:String): Boolean{
        return (email.isEmpty() || password.isEmpty())
    }


}