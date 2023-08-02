package com.twopoint.daggar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.twopoint.daggar2.di.DaggerUserRegistationComp
import com.twopoint.daggar2.di.NotficationProviders
import com.twopoint.daggar2.di.UserEmailServices
import com.twopoint.daggar2.di.UserRegServices
import com.twopoint.daggar2.di.UserRegistationComp
import javax.inject.Inject

class MainActivity :  AppCompatActivity() {
        @Inject
        lateinit var userRegServices: UserRegServices
        @Inject
        lateinit var emailServices: UserEmailServices
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component =    DaggerUserRegistationComp.builder()
            .notficationProviders(NotficationProviders(3))
            .build()

       component.injectchoda(this)

            userRegServices.getuserRegServices("hridoyh@gmailcom","qeqr")
            Log.i("TAG","Emptry")

    }
}