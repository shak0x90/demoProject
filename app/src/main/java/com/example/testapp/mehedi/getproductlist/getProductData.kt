package com.example.testapp.mehedi.getproductlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class getProductData(
    val id: Int,
    val images:List <Images>?,
    val name: String,
    val price: String
):Parcelable