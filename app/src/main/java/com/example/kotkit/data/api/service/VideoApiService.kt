package com.example.kotkit.data.api.service

import com.example.kotkit.data.dto.input.UpdateVideoInput
import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.Video
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApiService {
    @GET("videos/videos-of-user")
    suspend fun getVideosOfUser(
        @Query("userId") userId: Int,
        @Query("mode") mode: String,
    ): ApiResponse<List<Video>>

    @GET("videos/saved-videos-of-me")
    suspend fun getSavedVideosOfMe(): ApiResponse<List<Video>>

    @GET("videos/private-videos-of-me")
    suspend fun getPrivateVideosOfMe(): ApiResponse<List<Video>>

    @GET("videos/videos-of-friends")
    suspend fun getVideosOfFriends(): ApiResponse<List<Video>>

    @POST("videos/{videoId}/save")
    suspend fun updateSaveVideoState(
        @Path("videoId") videoId: Int
    ): ApiResponse<Video>

    @GET("videos/public-videos")
    suspend fun getAllPublicVideos(): ApiResponse<List<Video>>

    @GET("videos/private-videos")
    suspend fun getAllPrivateVideos(): ApiResponse<List<Video>>

    @GET("videos/all-videos")
    suspend fun getAllVideos(): ApiResponse<List<Video>>

    @Multipart
    @POST("videos/upload")
    suspend fun uploadVideo(
        @Part("title") title: RequestBody,
        @Part("mode") mode: RequestBody,
        @Part thumbnail: MultipartBody.Part?,
        @Part video: MultipartBody.Part
    ): ApiResponse<Video>

    @GET("videos/search")
    suspend fun searchVideos(
        @Query("q") query: String
    ): ApiResponse<List<Video>>

    @POST("videos/{videoId}/like")
    suspend fun updateLikeVideoState(
        @Path("videoId") videoId: Int
    ): ApiResponse<Video>

    @GET("videos/liked")
    suspend fun getLikedVideosOfMe(): ApiResponse<List<Video>>

    @POST("videos/{videoId}/update")
    suspend fun updateVideoInfo(
        @Path("videoId") videoId: Int,
        @Body updateVideoInput: UpdateVideoInput
    ): ApiResponse<Void>
}