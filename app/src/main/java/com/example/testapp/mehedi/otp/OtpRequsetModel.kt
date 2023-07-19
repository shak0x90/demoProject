package com.example.testapp.mehedi.otp

data class OtpRequsetModel(


    val is_forget_password: Boolean,
    val otp: String,
    val phone: String
)