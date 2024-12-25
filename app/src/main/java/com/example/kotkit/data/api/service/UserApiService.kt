package com.example.kotkit.data.api.service

import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.model.UserDetails
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("users/search")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ApiResponse<List<UserDetails>>
}