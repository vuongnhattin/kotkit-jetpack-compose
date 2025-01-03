package com.example.kotkit.data.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.dto.input.CommentInput
import com.example.kotkit.data.api.service.CommentApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentApiService: CommentApiService
) : ViewModel() {

    var comments by mutableStateOf<List<Comment>>(emptyList())
        private set

    var commentState by mutableStateOf<ApiState<Comment>>(ApiState.Empty())
        private set

    var allCommentsState by mutableStateOf<ApiState<List<Comment>>>(ApiState.Empty())
        private set

    fun createComment(videoId: Int, commentText: String) {
        fetchApi(
            stateSetter = { commentState = it },
            apiCall = {
                val commentInput = CommentInput(comment = commentText)
                val response = commentApiService.createComment(videoId, commentInput)
                if (commentState is ApiState.Success || response.status == 200) {
                    Log.i("API Send", "Success")
                    val newComment = response.data
                    newComment?.let {
                        comments = comments + it // Add the new comment to the list
                    }
                }
                response
            }
        )
    }

    fun getAllComments(videoId: Int) {
        fetchApi(
            stateSetter = { allCommentsState = it },
            apiCall = {
                val response = commentApiService.getAllCommentInVideo(videoId)
                if (allCommentsState is ApiState.Success || response.status == 200) {
                    Log.i("API Get ALL", "Success")
                    val listComment = response.data
                    if (listComment != null) {
                        comments = listComment
                    }
                }
                response
            }
        )
    }
}
//@HiltViewModel
//class CommentViewModel @Inject constructor(
//    private val commentApiService: CommentApiService
//) : ViewModel() {
//
//    var commentState by mutableStateOf<ApiState<Comment>>(ApiState.Empty())
//        private set
//    var allCommentsState by mutableStateOf<ApiState<List<Comment>>>(ApiState.Empty())
//        private set
//    fun createComment(videoId: Int, commentText: String) {
//        fetchApi(
//            stateSetter = { commentState = it },
//            apiCall = {
//                val commentInput = CommentInput(comment = commentText)
//                commentApiService.createComment(videoId, commentInput)
//            }
//        )
//
//    }
//    fun getAllComments(videoId: Int) {
//        fetchApi(
//            stateSetter = { allCommentsState = it },
//            apiCall = { commentApiService.getAllCommentInVideo(videoId) }
//        )
//    }
//}