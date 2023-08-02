package com.twopoint.daggar2.di

import android.util.Log
import javax.inject.Inject

interface NotificaionSercies{
    fun sendto(to:String,form:String,body:String)
}

class UserEmailServices @Inject constructor() :NotificaionSercies{
    override fun sendto(to:String, form:String, body:String){
        Log.d("TAG","user eamil services")
    }
}
class MagaageService(private val retrycount:Int) : NotificaionSercies{
    override fun sendto(to: String, form: String, body: String) {

        Log.d("TAG","user mesege sent $retrycount")
    }

}