package com.example.mystore.api

import com.example.mystore.api.data.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {

    @GET("/products")
    fun getProducts(@Query("limit") limit: Int): Call<ProductsResponse>
}


