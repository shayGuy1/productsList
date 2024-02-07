package com.example.mystore.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val name : String,
    val imageUrl : String,
    var totalInStock : Int = 0,
    val products: MutableList<Product> = mutableListOf()
) : Parcelable {
    fun getDistinctCount () = products.size
}
