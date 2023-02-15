package com.example.myapplication.data

class Repostory(private val userdao:Dao) {

    suspend fun getpassword(email:String)= userdao.getpassword(email)
    suspend fun existence(email: String) = userdao.if_exists(email)

    suspend fun adduser(user:User){
        userdao.adduser(user)
    }
}