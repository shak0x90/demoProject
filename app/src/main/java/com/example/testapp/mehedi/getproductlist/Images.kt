package com.example.testapp.mehedi.getproductlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val id: Int?,
    val image: String?
):Parcelable