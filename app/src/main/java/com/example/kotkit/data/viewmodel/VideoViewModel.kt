package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.VideoApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoApiService: VideoApiService
) : ViewModel() {
    var publicVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var privateVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var allVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Loading())
        private set

    var searchVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var selectedVideoToPlay by mutableStateOf<Video?>(null)
        private set

    fun selectVideoToPlay(video: Video) {
        selectedVideoToPlay = video
    }

//    fun getPublicVideosOfUser(userId: Int) {
//        fetchApi(stateSetter = { publicVideos = it }) {
//            // This is api call
//            val response = videoApiService.getVideosOfUser(userId, "public")
//            response
//        }
//    }
//
//    fun getPrivateVideosOfUser(userId: Int) {
//        fetchApi(stateSetter = { privateVideos = it }) {
//            val response = videoApiService.getVideosOfUser(userId, "private")
//            response
//        }
//    }

    fun getAllPublicVideos() {
        fetchApi(stateSetter = { publicVideos = it}) {
            val response = videoApiService.getAllPublicVideos()
            response
        }
    }

    fun getAllPrivateVideos() {
        fetchApi(stateSetter = { privateVideos = it}) {
            val response = videoApiService.getAllPrivateVideos()
            response
        }
    }

    fun getAllVideos() {
        fetchApi(stateSetter = { allVideos = it }) {
            val response = videoApiService.getAllVideos()
            response
        }
    }
    fun searchVideos(query: String){
        fetchApi(
            stateSetter = {searchVideos = it},
            apiCall = {
                val response = videoApiService.searchVideos(query)
                response
            }
        )
    }

}