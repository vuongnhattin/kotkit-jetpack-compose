package com.example.kotkit.data.viewmodel

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {
    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

    private val _selectedVideo = MutableStateFlow<Uri?>(null)
    val selectedVideo: StateFlow<Uri?> = _selectedVideo.asStateFlow()

    private val _isPreviewMode = MutableStateFlow(false)
    val isPreviewMode: StateFlow<Boolean> = _isPreviewMode.asStateFlow()

    private var videoCapture: VideoCapture<Recorder>? = null
    private var recordingState: Recording? = null
    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    fun onVideoSelected(uri: Uri?) {
        _selectedVideo.value = uri
        _isPreviewMode.value = uri != null
    }

    fun startCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        previewView: androidx.camera.view.PreviewView
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val qualitySelector = QualitySelector.fromOrderedList(
                listOf(Quality.HIGHEST, Quality.HD, Quality.SD),
                FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
            )

            val recorder = Recorder.Builder()
                .setQualitySelector(qualitySelector)
                .build()

            videoCapture = VideoCapture.withOutput(recorder)

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    videoCapture
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun toggleRecording(context: Context) {
        viewModelScope.launch {
            if (_isRecording.value) {
                recordingState?.stop()
                _isRecording.value = false
                _isPreviewMode.value = true
            } else {
                startRecording(context)
            }
        }
    }

    private fun startRecording(context: Context) {
        val videoCapture = videoCapture ?: return

        val name = "CameraX-video-" + SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.getDefault())
            .format(System.currentTimeMillis()) + ".mp4"

        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, name)
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/KotKit")
        }

        val mediaStoreOutput = MediaStoreOutputOptions.Builder(
            context.contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()

        // Kiểm tra quyền RECORD_AUDIO trước khi bắt đầu recording
        val hasAudioPermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.RECORD_AUDIO
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        try {
            recordingState = videoCapture.output
                .prepareRecording(context, mediaStoreOutput)
                .apply {
                    if (hasAudioPermission) {
                        // Enable audio recording
                        withAudioEnabled()
                    } else {
                        throw SecurityException("Recording requires RECORD_AUDIO permission")
                    }
                }
                .start(ContextCompat.getMainExecutor(context)) { event ->
                    when(event) {
                        is VideoRecordEvent.Start -> {
                            _isRecording.value = true
                        }
                        is VideoRecordEvent.Finalize -> {
                            if (!event.hasError()) {
                                val uri = event.outputResults.outputUri
                                _selectedVideo.value = uri
                                _isPreviewMode.value = true
                            } else {
                                event.cause?.printStackTrace()
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCleared() {
        super.onCleared()
        cameraExecutor.shutdown()
    }
}