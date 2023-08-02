package com.twopoint.daggar2.di

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class UserRegServices @Inject constructor(
    private val userRepo: UserRepo,
    @messege private val notificaionSercies: NotificaionSercies){

    fun getuserRegServices(email:String,pass:String) {
        userRepo.saveUser(email,pass)
        notificaionSercies.sendto(email,"hridoy@ndn.com","User Registerd")
        Log.d("TAG","getUserReg")
    }
}