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
    retrofit: Retrofit
) : ViewModel() {
    private val videoApi = retrofit.create(VideoApiService::class.java)

    var publicVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Loading())
        private set

    var privateVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Loading())
        private set

    fun getPublicVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { publicVideos = it }) {
//            val response = videoApi.getVideosOfUser(userId, "public").data!!
            val response = VideoMock.publicVideos.data ?: emptyList()
            println("video response: $response")
            response
        }
    }

    fun getPrivateVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { privateVideos = it }) {
            val response = VideoMock.privateVideos.data ?: emptyList()
            response
        }
    }
}