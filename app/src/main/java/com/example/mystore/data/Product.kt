package com.example.mystore.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name : String,
    val imageUrl : String,
    val price : Double,
    val stockQuantity : Int) : Parcelable
