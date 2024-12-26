package com.example.kotkit.ui.screen

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.VideoMode
import com.example.kotkit.data.viewmodel.UploadVideoViewModel
import com.example.kotkit.ui.utils.DisplayApiResult
import com.example.kotkit.ui.utils.ErrorSnackBar

@Composable
fun UploadVideoScreen(
    videoUri: Uri?,
    modifier: Modifier = Modifier,
) {
    val uploadVideoViewModel: UploadVideoViewModel = hiltViewModel()
    var title by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf(VideoMode.PUBLIC) }
    var thumbnailUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val navController = LocalNavController.current

    var messageError by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf("") }
    var videoError by remember { mutableStateOf("") }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        thumbnailUri = uri
    }

    val onNavigateBack = {
        navController.navigate("camera") {
            popUpTo("camera") {
                inclusive = true
            }
        }
    }
    val uploadState = uploadVideoViewModel.uploadState

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
                                    thumbnailUri = thumbnailUri,
                                    title = title,
                                    mode = mode
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = Color.Black
                    )
                }

                Text(
                    text = "Thông tin video",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Box(modifier = Modifier.size(48.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(9f/16f)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
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

            val focusManager = LocalFocusManager.current

            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    "Tiêu đề",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Nhập tiêu đề cho video của bạn") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { focusManager.clearFocus() },
                    singleLine = true
                )
            }

            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    "Chế độ hiển thị",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                VideoMode.values().forEach { type ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = mode == type,
                            onClick = { mode = type }
                        )
                        Text(
                            text = type.getDisplayName(),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            // Thumbnail Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    "Ảnh thumbnail (tùy chọn)",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (thumbnailUri != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        AsyncImage(
                            model = thumbnailUri,
                            contentDescription = "Thumbnail Preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                OutlinedButton(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Chọn ảnh thumbnail",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(if (thumbnailUri == null) "Thêm ảnh thumbnail" else "Thay đổi ảnh thumbnail")
                }
            }

            DisplayApiResult(uploadState,
                onError = { error ->
                    when (error.code) {
                        "VALIDATION_ERROR" -> error.data?.let { data ->
                            val dataMap = data as Map<String, String>
                            titleError = when (dataMap["title"]) {
                                "TITLE_REQUIRED" -> "Tiêu đề không được để trống!"
                                else -> ""
                            }
                            videoError = when (dataMap["video"]) {
                                "VIDEO_REQUIRED" -> "Không có Video!"
                                "PAYLOAD_TOO_LARGE" -> "Dung lượng video quá lớn (không được quá 100MB)!"
                                else -> ""
                            }
                        }
                        else -> messageError = "Lỗi không xác định!"
                    }

                    if (videoError.isNotEmpty())
                        messageError = videoError
                    else if (titleError.isNotEmpty())
                        messageError = titleError

                    AlertDialog(
                        onDismissRequest = { /* Dismiss the dialog */ },
                        title = { Text("Đăng video thất bại") },
                        text = { Text(messageError) },
                        confirmButton = {
                            TextButton(onClick = {
                                messageError = ""
                                uploadVideoViewModel.setEmptyState()
                            }) {
                                Text("OK")
                            }
                        }
                    )
                }
            ) {
                AlertDialog(
                    onDismissRequest = { /* Dismiss the dialog */ },
                    title = { Text("Đăng video thành công") },
                    text = { Text("Video của bạn đã được đăng thành công!") },
                    confirmButton = {
                        TextButton(onClick = {
                            navController.navigate("home")
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}