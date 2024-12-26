package com.example.kotkit.data.api.service

import com.example.kotkit.data.dto.response.ApiResponse
import retrofit2.http.PUT
import retrofit2.http.Query

interface FriendApiService {
    @PUT("unfriend")
    suspend fun unfriend(
        @Query("userId") userId: Int
    ) : ApiResponse<Void>

    @PUT("send-friend-request")
    suspend fun sendFriendRequest(
        @Query("userId") userId: Int
    ) : ApiResponse<Void>

    @PUT("take-back-friend-request")
    suspend fun takeBackFriendRequest(
        @Query("userId") userId: Int
    ) : ApiResponse<Void>

    @PUT("reject-friend-request")
    suspend fun rejectFriendRequest(
        @Query("userId") userId: Int
    ) : ApiResponse<Void>

    @PUT("accept-friend-request")
    suspend fun acceptFriendRequest(
        @Query("userId") userId: Int
    ) : ApiResponse<Void>
}