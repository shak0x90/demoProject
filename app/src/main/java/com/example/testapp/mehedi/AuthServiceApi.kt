package com.example.testapp.mehedi

import com.example.testapp.mehedi.regitation.RegisterRequestModel
import com.example.testapp.mehedi.regitation.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface  AuthServiceApi {
    @POST("/api/v1/auth/login")
    fun userLogin(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>

    @POST("/api/v1/auth/user-register")
    fun userRegister(@Body registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>

//    @POST("/api/v1/auth/verify-otp")
//    fun verifyOTP(@Body otpRequestModel: OtpRequestModel): Call<OtpResponseModel>


}
