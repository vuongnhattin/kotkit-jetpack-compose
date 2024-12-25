package com.example.kotkit.data.api

import com.example.kotkit.data.api.interceptor.AuthInterceptor
import com.example.kotkit.data.localstorage.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//const val BASE_URL = "http://192.168.1.7:8080/"

// Tin's IP
const val BASE_URL = "http://192.168.23.167:8080/"

const val BASE_URL_EMULATOR = "http://10.0.2.2:8080/"
const val BASE_URL_REMOTE_DEVICE = "http://192.168.1.214:8080/"
const val BASE_URL_MINIO = "http://192.168.1.214:8080/"

fun retrofit(tokenManager: TokenManager): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenManager)) // Add the custom interceptor
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL) // Replace with your base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()) // Use appropriate converter
        .build()
}


