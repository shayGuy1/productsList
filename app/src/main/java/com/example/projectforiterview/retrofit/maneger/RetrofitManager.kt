package com.example.projectforiterview.retrofit.maneger

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.123:8030/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit {
       return retrofit
    }
}