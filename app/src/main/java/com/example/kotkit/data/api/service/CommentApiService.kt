package com.example.kotkit.data.api.service

import com.example.kotkit.data.dto.input.CommentInput
import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.Comment
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApiService {
    @POST("comment/{videoId}")
    suspend fun createComment(
        @Path("videoId") videoId: Int,
        @Body commentInput: CommentInput
    ): ApiResponse<Comment>

    @GET("comment/{videoId}")
    suspend fun getAllCommentInVideo(
        @Path("videoId") videoId: Int,
    ): ApiResponse<List<Comment>>
}