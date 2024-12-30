package com.example.kotkit.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.LocalVideoViewModel
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.presentation.components.VideoPlayerComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayVideoScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val videoViewModel: VideoViewModel = LocalVideoViewModel.current

    Box(modifier = modifier.fillMaxSize()) {
        // Video player ở background
        videoViewModel.selectedVideoToPlay?.let {
            VideoPlayerComponent(
                modifier = Modifier.fillMaxSize(),
                video = it
            )
        }

        // TopBar nằm trên cùng
        TopAppBar(
            modifier = Modifier.align(Alignment.TopStart),
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }

//    Scaffold(
//        modifier = modifier.fillMaxSize(),
//        topBar = {
//            Surface(
//                modifier = Modifier.zIndex(1f),
//                color = Color.Transparent
//            ) {
//                TopAppBar(
//                    title = { },
//                    navigationIcon = {
//                        IconButton(onClick = { navController.popBackStack() }) {
//                            Icon(
//                                Icons.Filled.ArrowBack,
//                                contentDescription = "Back",
//                                tint = Color.Black
//                            )
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Color.Transparent
//                    )
//                )
//            }
//        },
//        containerColor = Color.Transparent, // Làm cho Scaffold trong suốt
//        contentWindowInsets = WindowInsets(0,0,0,0),
//        content = { _ ->
//            videoViewModel.selectedVideoToPlay?.let {
//                VideoPlayerComponent(
//                    modifier = Modifier
//                        .padding(0.dp)
//                        .fillMaxSize(),
//                    video = it
//                )
//            }
//        }
//    )
}