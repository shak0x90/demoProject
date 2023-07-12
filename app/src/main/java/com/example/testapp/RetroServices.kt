package com.example.testapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
const val baseUrl = "http://178.128.207.239:8010"

interface RetroServices {

    @POST("/api/v1/auth/login")
    fun getUser(@Body requestBody: LoginRequest): Call<UserLogin>
}