package com.example.testapp.mehedi.regitation

import android.os.Parcelable


data class RegisterRequestModel(
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone: String,
    val email: String
)
