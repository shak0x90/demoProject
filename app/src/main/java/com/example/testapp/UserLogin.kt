package com.example.testapp

data class UserLogin(
    var success: Boolean? = null,
    var message: String? = null,
    var data: Data? = null
) data class Data(
    val access_token: String,
    val phone: String,
    val refresh_token: String,
    val service_provider_id:  Any,
    val user_id: Int,
    val user_profile_id: Int
)