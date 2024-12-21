package com.example.kotkit.data.api.service

import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.Video
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface VideoApiService {
    @GET("videos/videos-of-user")
    suspend fun getVideosOfUser(
        @Query("userId") userId: Int,
        @Query("visibility") visibility: String,
    ): ApiResponse<List<Video>>

    @Multipart
    @POST("videos")
    suspend fun uploadVideo(
        @Part("title") title: RequestBody,
        @Part("creatorId") creatorId: RequestBody,
        @Part("mode") mode: RequestBody,
        @Part file: MultipartBody.Part
    ): ApiResponse<Video>
}