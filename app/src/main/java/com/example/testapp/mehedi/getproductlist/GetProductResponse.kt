package com.example.testapp.mehedi.getproductlist

data class GetProductResponse(
    val count: Int,
    val `data`: List<getProductData>,
    val links: Links,
    val messsage: String,
    val success: Boolean
)