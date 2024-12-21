package com.example.kotkit.data.api.service

import com.example.kotkit.data.api.input.CommentInput
import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.model.UserDetails
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApiService {
    @POST("comment/{videoId}")
    suspend fun createComment(
        @Path("videoId") videoId: Int,
        @Body commentInput: CommentInput
    ): ApiResponse<Comment>
}