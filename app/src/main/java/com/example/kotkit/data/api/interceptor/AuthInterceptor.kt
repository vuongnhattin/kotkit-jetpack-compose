package com.example.kotkit.data.api.interceptor

import com.example.kotkit.data.localstorage.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = tokenManager.getToken()
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3VAZW1haWwuY29tIiwiaWF0IjoxNzM0NzQyOTg0LCJleHAiOjE3NDQ3NDI5ODR9.0KVaEmxH7AtRS7APLf-2_NX5lYmqB916DsFJToRgD4g"
        val request = if (!token.isNullOrEmpty()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}