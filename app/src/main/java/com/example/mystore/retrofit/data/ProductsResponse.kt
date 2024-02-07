package com.example.mystore.retrofit.data

import com.google.gson.annotations.SerializedName

class ProductsResponse(
    @SerializedName("products")
    val products: List<Product>
)