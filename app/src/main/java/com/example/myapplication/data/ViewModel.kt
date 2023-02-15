package com.example.myapplication.data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MoviesListFragment
import kotlinx.coroutines.launch

class ViewModel(application:Application) : AndroidViewModel(application) {
    lateinit var repostory: Repostory
    lateinit var movierepo: MovieRepo
    val fragment: MoviesListFragment = MoviesListFragment()
    init {
        val userDao = UserDatabase.getDatabase(application).userdao()
        repostory = Repostory(userDao)

    }
    fun getpassword(email:String):String{
        var password: String = "00000000"
        viewModelScope.launch {
            password = repostory.getpassword(email)
        }
        return password
    }

    fun existence(email: String): String? {
        var exist: String? = null
        viewModelScope.launch {
            exist = repostory.existence(email)
        }
        return exist
    }

    fun adduser(user:User){
        viewModelScope.launch {
            repostory.adduser(user)
        }
    }
}