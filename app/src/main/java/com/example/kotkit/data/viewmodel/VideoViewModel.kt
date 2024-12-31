package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.VideoApiService
import com.example.kotkit.data.dto.input.UpdateVideoInput
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

    var updateLikeVideo by mutableStateOf<ApiState<Video>>(ApiState.Empty())
        private set

    var likedVideos by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var likedVideosList by mutableStateOf<List<Int>>(emptyList())
        private set

    var publicVideosOfUser by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var friendVideosOfUser by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var privateVideosOfMe by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var savedVideosOfMe by mutableStateOf<ApiState<List<Video>>>(ApiState.Empty())
        private set

    var savedVideosList by mutableStateOf<List<Int>>(emptyList())
        private set

    var updateSaveVideo by mutableStateOf<ApiState<Video>>(ApiState.Empty())
        private set

    fun getPublicVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { publicVideosOfUser = it }) {
            // This is api call
            videoApiService.getVideosOfUser(userId, "PUBLIC")
        }
    }

    fun getFriendVideosOfUser(userId: Int) {
        fetchApi(stateSetter = { friendVideosOfUser = it }) {
            videoApiService.getVideosOfUser(userId, "FRIEND")
        }
    }

    fun getPrivateVideosOfMe() {
        fetchApi(stateSetter = { privateVideosOfMe = it }) {
            videoApiService.getPrivateVideosOfMe()
        }
    }

    fun getSavedVideosOfMe() {
        fetchApi(stateSetter = { state ->
            savedVideosOfMe = state
            if (state is ApiState.Success) {
                savedVideosList = state.data?.map { it.videoId } ?: emptyList()
            }
        }) {
            val response = videoApiService.getSavedVideosOfMe()
            response
        }
    }

    fun getAllPublicVideos() {
        fetchApi(stateSetter = { publicVideos = it }) {
            val response = videoApiService.getAllPublicVideos()
            response
        }
    }

    fun getAllPrivateVideos() {
        fetchApi(stateSetter = { privateVideos = it }) {
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

    fun searchVideos(query: String) {
        fetchApi(
            stateSetter = { searchVideos = it },
            apiCall = {
                val response = videoApiService.searchVideos(query)
                response
            }
        )
    }

    private fun updateVideoInList(
        videos: ApiState<List<Video>>,
        updatedVideo: Video
    ): ApiState<List<Video>> {
        return when (videos) {
            is ApiState.Success -> {
                ApiState.Success(
                    videos.data?.map { video ->
                        if (video.videoId == updatedVideo.videoId) updatedVideo else video
                    }
                )
            }

            else -> videos
        }
    }

    fun isVideoLiked(videoId: Int): Boolean = likedVideosList.contains(videoId)

    fun isVideoSaved(videoId: Int): Boolean = savedVideosList.contains(videoId)

    fun updateLikeVideoState(videoId: Int) {
        fetchApi(stateSetter = { newState ->
            updateLikeVideo = newState

            if (newState is ApiState.Success) {
                val updatedVideo = newState.data

                allVideos = updatedVideo?.let { updateVideoInList(allVideos, it) }!!

                publicVideos = updateVideoInList(publicVideos, updatedVideo)

                privateVideos = updateVideoInList(privateVideos, updatedVideo)

                likedVideosList = if (likedVideosList.contains(videoId)) {
                    likedVideosList - videoId
                } else {
                    likedVideosList + videoId
                }
            }
        }) {
            val response = videoApiService.updateLikeVideoState(videoId)
            response
        }
    }

    fun updateSaveVideoState(videoId: Int) {
        fetchApi(stateSetter = { newState ->
            updateSaveVideo = newState

            if (newState is ApiState.Success) {
                savedVideosList = if (savedVideosList.contains(videoId)) {
                    savedVideosList - videoId
                } else {
                    savedVideosList + videoId
                }
            }
        }) {
            val response = videoApiService.updateSaveVideoState(videoId)
            response
        }
    }

    fun getLikedVideosOfMe() {
        fetchApi(stateSetter = { state ->
            likedVideos = state
            if (state is ApiState.Success) {
                likedVideosList = state.data?.map { it.videoId } ?: emptyList()
            }
        }) {
            val response = videoApiService.getLikedVideosOfMe()
            response
        }
    }

    var editState by mutableStateOf<ApiState<Void>>(ApiState.Empty())

    fun updateVideoInfo(
        videoId: Int,
        updateVideoInput: UpdateVideoInput
    ) {
        fetchApi(
            stateSetter = { editState = it},
            apiCall = {
                val response = videoApiService.updateVideoInfo(videoId, updateVideoInput)
                response
            }
        )
    }
}