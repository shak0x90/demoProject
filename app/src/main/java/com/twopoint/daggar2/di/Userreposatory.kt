package com.twopoint.daggar2.di

import android.util.Log
import javax.inject.Inject
interface UserRepo{
    fun saveUser(email:String,password:String)
}

class UserRepoSql @Inject constructor():UserRepo{
    override fun saveUser(email:String, password:String){
        Log.d("TAG","UserRepoSql Class called")
    }
}
class FirebaseRepo:UserRepo{
    override fun saveUser(email: String, password: String) {
        Log.d("TAG","ForebaseRepo Class called")
    }

}