package com.example.mystore.api.maneger

import com.example.mystore.application.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().cache(Cache(App.getContext().cacheDir, CACHE_SIZE)).build())
        .build()

    fun getInstance() : Retrofit {
       return retrofit
    }
}

