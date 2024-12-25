package com.example.kotkit.data.api.service

import com.example.kotkit.data.dto.input.LoginInput
import com.example.kotkit.data.dto.input.RegisterInput
import com.example.kotkit.data.dto.response.LoginResponse
import com.example.kotkit.data.dto.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Body loginInput: LoginInput
    ): ApiResponse<LoginResponse>

    @POST("auth/register")
    suspend fun register(
        @Body registerInput: RegisterInput
    ): ApiResponse<Void>
}

