package com.example.projectforiterview.retrofit

import com.example.projectforiterview.retrofit.data.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {

    @GET("/products")
    fun getProducts(@Query("limit") limit: Int, ): Call<ProductsResponse>
}


