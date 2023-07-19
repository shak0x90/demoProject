package com.example.testapp.mehedi.getServiceList

data class GetServiceModel(
    val count: Int,
    val `data`: List<Data>,
    val links: Links,
    val messsage: String,
    val success: Boolean
)