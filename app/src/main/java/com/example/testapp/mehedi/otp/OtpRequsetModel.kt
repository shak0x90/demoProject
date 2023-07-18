package com.example.testapp.mehedi.otp

data class OtpRequsetModel(


    val phone: String,
    val otp: String,
    val is_forget_password: Boolean
)