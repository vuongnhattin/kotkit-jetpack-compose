package com.example.kotkit.data.api.service

import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.Video
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideoApiService {
    @GET("videos-of-user")
    suspend fun getVideosOfUser(
        @Query("userId") userId: Int,
        @Query("visibility") visibility: String
    ): ApiResponse<List<Video>>
}