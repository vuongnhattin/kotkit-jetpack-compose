package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.VideoApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.mock.VideoMock
import com.example.kotkit.data.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoApiService: VideoApiService
) : ViewModel() {
    var publicVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var privateVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    fun getPublicVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { publicVideos = it }) {
            // This is api call
//            val response = videoApiService.getVideosOfUser(userId, "public")

            // This is mock call
            val response = VideoMock.publicVideos

            response
        }
    }

    fun getPrivateVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { privateVideos = it }) {
//            val response = videoApiService.getVideosOfUser(userId, "private")

            val response = VideoMock.privateVideos

            response
        }
    }
}