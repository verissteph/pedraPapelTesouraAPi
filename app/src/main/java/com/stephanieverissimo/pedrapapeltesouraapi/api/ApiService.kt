package com.stephanieverissimo.pedrapapeltesouraapi.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.toys/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val toysApi: ToysApi = retrofit.create(ToysApi::class.java)
}