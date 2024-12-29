package com.example.kotkit.data.api.service

import com.example.kotkit.data.dto.response.ApiResponse
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

    @GET("users/{userId}/details")
    suspend fun getUserDetails(
        @Path("userId") userId: Int
    ): ApiResponse<UserDetails>

    @GET("users/{userId}/friends")
    suspend fun getFriendsOfUser(
        @Path("userId") userId: Int
    ): ApiResponse<List<UserDetails>>

    @GET("me")
    suspend fun getMe(): ApiResponse<UserDetails>
}