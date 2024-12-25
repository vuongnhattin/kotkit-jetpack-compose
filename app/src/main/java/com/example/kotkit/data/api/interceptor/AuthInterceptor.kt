package com.example.kotkit.data.api.interceptor

import com.example.kotkit.data.localstorage.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = tokenManager.getToken()
//        if (token.isNullOrEmpty())
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb25nQGVtYWlsIiwiaWF0IjoxNzM0OTY5OTU2LCJleHAiOjE3NDQ5Njk5NTZ9.t5fl-BbYg96hhIB_0QTErfRVk8ecUqE_vptlO8ktS-w";
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