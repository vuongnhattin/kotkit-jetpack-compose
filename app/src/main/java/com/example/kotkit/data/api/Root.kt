package com.example.kotkit.data.api

import com.example.kotkit.data.api.interceptor.AuthInterceptor
import com.example.kotkit.data.localstorage.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//const val BASE_URL = "http://192.168.1.7:8080/"

// Tin's IP
//const val BASE_URL = "http://192.168.241.167"

// emulator's IP
const val BASE_URL = "http://10.0.2.2"

const val BACKEND_URL = "${BASE_URL}:8080/"
const val BASE_URL_MINIO = "${BASE_URL}:9001"
// BASE_URL_MINIO khong co dau / o cuoi nhe dm Tin

fun retrofit(tokenManager: TokenManager): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenManager)) // Add the custom interceptor
        .build()

    return Retrofit.Builder()
        .baseUrl(BACKEND_URL) // Replace with your base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()) // Use appropriate converter
        .build()
}


