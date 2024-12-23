package com.example.kotkit.data.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.VideoApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.model.VideoMode
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UploadVideoViewModel @Inject constructor(
    private val videoApiService: VideoApiService,
) : ViewModel() {
    var uploadState by mutableStateOf<ApiState<Video>>(ApiState.Loading())
        private set

    fun uploadVideo(
        context: Context,
        videoUri: Uri?,
        title: String,
        videoMode: VideoMode
    ) {
        if (videoUri == null) {
            uploadState = ApiState.Error(
                data = null,
                status = 400,
                code = "INVALID_VIDEO"
            )
            return
        }

        val videoFile = getFileFromUri(context, videoUri)

        val titleRequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val modeRequestBody = videoMode.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val videoRequestBody = videoFile.asRequestBody("video/mp4".toMediaTypeOrNull())
        val videoPart = MultipartBody.Part.createFormData(
            "file",
            videoFile.name,
            videoRequestBody
        )

        fetchApi(
            stateSetter = { uploadState = it },
            apiCall = {
                val response = videoApiService.uploadVideo(
                    title = titleRequestBody,
                    mode = modeRequestBody,
                    file = videoPart
                )

                response
            }
        )
    }

    private fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("video", ".mp4", context.cacheDir)
        tempFile.outputStream().use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        return tempFile
    }
}