package com.example.kotkit.ui.screen

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.VideoMode
import com.example.kotkit.data.viewmodel.UploadVideoViewModel

@Composable
fun UploadVideoScreen(
    videoUri: Uri?,
    modifier: Modifier = Modifier,
) {
    val uploadVideoViewModel: UploadVideoViewModel = hiltViewModel()
    var title by remember { mutableStateOf("") }
    var videoMode by remember { mutableStateOf(VideoMode.PUBLIC) }
    val context = LocalContext.current
    val navController = LocalNavController.current
    val onNavigateBack = {
        navController.navigate("camera") {
            popUpTo("camera") {
                inclusive = true
            }
        }
    }

    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onNavigateBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Hủy", color = Color.Black)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                uploadVideoViewModel.uploadVideo(
                                    context = context,
                                    videoUri = videoUri,
                                    title = title,
                                    videoMode = videoMode
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Đăng")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = Color.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(9f/16f)
                    .align(Alignment.CenterHorizontally)
            ) {
                AndroidView(
                    factory = { context ->
                        VideoView(context).apply {
                            setVideoURI(videoUri)
                            setMediaController(MediaController(context).apply {
                                setAnchorView(this@apply)
                            })
                            start()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Tiêu đề") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text("Chế độ hiển thị", style = MaterialTheme.typography.titleMedium)
                VideoMode.values().forEach { type ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = videoMode == type,
                            onClick = { videoMode = type }
                        )
                        Text(
                            text = type.getDisplayName(),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}