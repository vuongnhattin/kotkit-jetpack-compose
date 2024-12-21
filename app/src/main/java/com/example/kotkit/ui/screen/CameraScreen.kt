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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.camera.view.PreviewView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.ui.icon.Gallery_thumbnail
import com.example.kotkit.data.viewmodel.CameraViewModel

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

    // Define required permissions
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

    // Check if we have permissions
    var hasCameraPermission by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        hasCameraPermission = permissionsMap.values.reduce { acc, next -> acc && next }
    }

    // Gallery launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            cameraViewModel.onVideoSelected(it)
            navController.navigate("upload-video/${Uri.encode(it.toString())}")
        }
    }

    // Request permissions when the component is first launched
    LaunchedEffect(key1 = true) {
        permissionLauncher.launch(permissions.toTypedArray())
    }

    // Handle navigation to upload screen after recording
    LaunchedEffect(selectedVideo) {
        selectedVideo?.let { uri ->
            if (isPreviewMode) {
                navController.navigate("upload-video/${Uri.encode(uri.toString())}")
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            // Camera Preview
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

            // Camera Controls
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

                // Recording and Gallery Controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Empty space for layout balance
                    Box(modifier = Modifier.weight(1f)) {}

                    // Record Button
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

                    // Gallery Button
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