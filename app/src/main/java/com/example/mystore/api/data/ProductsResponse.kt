package com.example.mystore.api.data

import com.google.gson.annotations.SerializedName

class ProductsResponse(
    @SerializedName("products")
    val products: List<ProductWS>
)