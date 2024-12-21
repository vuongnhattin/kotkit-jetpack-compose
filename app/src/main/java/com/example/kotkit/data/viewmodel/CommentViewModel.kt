package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.input.CommentInput
import com.example.kotkit.data.api.service.CommentApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.di.ApiServiceModule
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentApiService: CommentApiService
) : ViewModel() {

    var commentState by mutableStateOf<ApiState<Comment>>(ApiState.Loading())
        private set

    fun createComment(videoId: Int, commentText: String) {
        fetchApi(
            stateSetter = { commentState = it },
            apiCall = {
                val commentInput = CommentInput(comment = commentText)
                commentApiService.createComment(videoId, commentInput)
            }
        )
    }
}