package com.example.kotkit.data.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.webkit.MimeTypeMap
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
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class UploadVideoViewModel @Inject constructor(
    private val videoApiService: VideoApiService,
) : ViewModel() {
    var uploadState by mutableStateOf<ApiState<Video>>(ApiState.Empty())
        private set

    fun uploadVideo(
        context: Context,
        videoUri: Uri?,
        thumbnailUri: Uri?,
        title: String,
        mode: VideoMode
    ) {
        if (videoUri == null) {
            uploadState = ApiState.Error(
                data = null,
                status = 400,
                code = "INVALID_VIDEO"
            )
            return
        }

        val videoFile = getFileFromUri(context, videoUri, "video", ".mp4")

        // Get thumbnail file - either from user selection or extract from video
        val thumbnailFile = thumbnailUri?.let {
            getFileFromUri(context, it, "thumbnail", getFileExtension(context, it))
        } ?: extractThumbnailFromVideo(context, videoUri)

        val titleRequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val modeRequestBody = mode.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val videoRequestBody = videoFile.asRequestBody("video/mp4".toMediaTypeOrNull())
        val videoPart = MultipartBody.Part.createFormData(
            "video",
            videoFile.name,
            videoRequestBody
        )

        val thumbnailPart = thumbnailFile?.let {
            val thumbnailRequestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(
                "thumbnail",
                it.name,
                thumbnailRequestBody
            )
        }

        fetchApi(
            stateSetter = { uploadState = it },
            apiCall = {
                val response = videoApiService.uploadVideo(
                    title = titleRequestBody,
                    mode = modeRequestBody,
                    video = videoPart,
                    thumbnail = thumbnailPart
                )

                response
            }
        )
    }

    private fun extractThumbnailFromVideo(context: Context, videoUri: Uri): File? {
        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(context, videoUri)

            // Get frame at 1 microsecond (essentially the first frame)
            val bitmap = retriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)

            if (bitmap != null) {
                // Create temporary file for the thumbnail
                val thumbnailFile = File.createTempFile("thumbnail", ".jpg", context.cacheDir)

                // Compress and save the bitmap as JPEG
                FileOutputStream(thumbnailFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                }

                bitmap.recycle()
                retriever.release()

                thumbnailFile
            } else {
                retriever.release()
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getFileFromUri(
        context: Context,
        uri: Uri,
        prefix: String,
        extension: String
    ): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile(prefix, extension, context.cacheDir)
        tempFile.outputStream().use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        return tempFile
    }

    private fun getFileExtension(context: Context, uri: Uri): String {
        val mimeType = context.contentResolver.getType(uri)
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(mimeType)
            ?.let { ".$it" }
            ?: ".jpg"
    }

    fun setEmptyState() {
        uploadState = ApiState.Empty()
    }
}