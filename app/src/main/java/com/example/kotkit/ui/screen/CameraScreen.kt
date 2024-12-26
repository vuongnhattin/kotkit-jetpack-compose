package com.example.kotkit.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.camera.view.PreviewView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.ui.icon.Gallery_thumbnail
import com.example.kotkit.data.viewmodel.CameraViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
) {
    val cameraViewModel: CameraViewModel = hiltViewModel()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navController = LocalNavController.current

    val isRecording by cameraViewModel.isRecording.collectAsState()
    val isPreviewMode by cameraViewModel.isPreviewMode.collectAsState()
    val selectedVideo by cameraViewModel.selectedVideo.collectAsState()

    // Timer state
    var elapsedTime by remember { mutableStateOf(0) }

    // Timer effect
    LaunchedEffect(isRecording) {
        elapsedTime = 0
        while (isRecording) {
            delay(1000)
            elapsedTime++
        }
    }

    // Format time function
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds)
    }

    // Rest of the permissions code remains the same...
    val permissions = remember {
        mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply {
            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        hasCameraPermission = permissionsMap.values.reduce { acc, next -> acc && next }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            cameraViewModel.onVideoSelected(it)
            navController.navigate("upload-video/${Uri.encode(it.toString())}")
        }
    }

    LaunchedEffect(key1 = true) {
        permissionLauncher.launch(permissions.toTypedArray())
    }

    LaunchedEffect(selectedVideo) {
        selectedVideo?.let { uri ->
            if (isPreviewMode) {
                navController.navigate("upload-video/${Uri.encode(uri.toString())}")
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            AndroidView(
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    }.also { previewView ->
                        cameraViewModel.startCamera(context, lifecycleOwner, previewView)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            if (!isPreviewMode) {
                // Back Button
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = Color.White
                    )
                }

                // Recording Timer
                if (isRecording) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 16.dp)
                            .background(Color.Black.copy(alpha = 0.6f))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = formatTime(elapsedTime),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Recording and Gallery Controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {}

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f)
                                .border(
                                    width = 4.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .clickable { cameraViewModel.toggleRecording(context) },
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(if (isRecording) 0.5f else 0.8f)
                                    .background(
                                        color = if (isRecording) Color.Red else Color.White,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        IconButton(
                            onClick = { galleryLauncher.launch("video/*") }
                        ) {
                            Icon(
                                imageVector = Gallery_thumbnail,
                                contentDescription = "Chọn video từ thư viện",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}