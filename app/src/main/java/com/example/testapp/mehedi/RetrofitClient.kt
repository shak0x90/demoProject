package com.example.testapp.mehedi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getService(): AuthServiceApi {

        val interpeter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interpeter)
        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://178.128.207.239:8010")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AuthServiceApi::class.java)
    }

}
