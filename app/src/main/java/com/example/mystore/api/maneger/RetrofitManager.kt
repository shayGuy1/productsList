package com.example.mystore.api.maneger

import com.example.mystore.application.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    // The Retrofit instance below will cache all responses until it reached the 10 MB maximum.
    // After it exceeded the cache disk limit, it'll clean up the oldest entries.
    // OkHttp will automatically apply Etag, Cache-Control, etc logic on every request for us.
    // If the resource stayed the same, it won't be loaded again

    // Note that this kind of cache will still perform a network call to check modifications.
    // But instead of getting the entire response's body over and over again,
    // it will get only the required headers (HEAD call) and check with them.

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

