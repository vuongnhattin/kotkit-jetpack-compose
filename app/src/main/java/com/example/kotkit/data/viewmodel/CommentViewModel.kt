package com.example.kotkit.data.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.input.CommentInput
import com.example.kotkit.data.api.service.CommentApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.di.ApiServiceModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentApiService: CommentApiService
) : ViewModel() {

    var commentState by mutableStateOf<ApiState<Comment>>(ApiState.Loading())
        private set
    var allCommentsState by mutableStateOf<ApiState<List<Comment>>>(ApiState.Loading())
        private set

    fun createComment(videoId: Int, commentText: String) {
        fetchApi(
            stateSetter = { commentState = it },
            apiCall = {
                val commentInput = CommentInput(comment = commentText)
                commentApiService.createComment(videoId, commentInput)
            }
        )
        viewModelScope.launch {
            Log.e("Comment View Model", "OUTSIDE")
            if (commentState is ApiState.Success) {
                Log.e("Comment View Model", "if 1")
                val newComment = (commentState as ApiState.Success).data
                if (newComment != null && allCommentsState is ApiState.Success) {
                    Log.e("Comment View Model", "if 2")
                    val currentComments = (allCommentsState as ApiState.Success).data?.toMutableList() ?: mutableListOf()
                    currentComments.add(newComment)
                    allCommentsState = ApiState.Success(currentComments, status = 200, code = "SUCCESS")
                }
            }
        }
    }
    fun getAllComments(videoId: Int) {
        fetchApi(
            stateSetter = { allCommentsState = it },
            apiCall = { commentApiService.getAllCommentInVideo(videoId) }
        )
    }
}