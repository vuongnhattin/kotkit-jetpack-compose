package com.example.kotkit.ui.screen

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.mock.VideoMockData
import com.example.kotkit.presentation.components.VideoPlayerComponent
import com.example.kotkit.ui.icon.Search

@Composable
private fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp)
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val videoUri = Uri.parse("android.resource://${context.packageName}/raw/video_test")
    val navController = LocalNavController.current

    // Video config
    // State cho selected tab
    var selectedTab by remember { mutableStateOf(0) }
    // State cho video mode
    var videoMode by remember { mutableStateOf(VideoMode.PUBLIC) }
    val videos = VideoMockData.videos

    // ExoPlayer setup
    val loadControl = DefaultLoadControl.Builder()
        .setBufferDurationsMs(
            5000,  // minBufferMs
            50000, // maxBufferMs
            1500,  // bufferForPlaybackMs
            2000   // bufferForPlaybackAfterRebufferMs
        ).build()

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setLoadControl(loadControl)
            .build()
            .apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            playWhenReady = true
        }
    }

    // Cleanup
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    val pagerState = rememberPagerState(pageCount = { videos.size })


    Box(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize(),
    ) {
        // Video Pager
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            VideoPlayerComponent(
                video = videos[page],
                exoPlayer = exoPlayer,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center
        ) {

            val elevation = 32.dp
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                val texts = listOf("Đề xuất", "Bạn bè")

                for (text in texts) {
                    Column(
                        modifier = Modifier
                            .shadow(elevation = elevation)
                    ) {
                        Text(
                            text = text,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.navigate("search/")
                }) {
                    Icon(
                        Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .shadow(elevation = elevation)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}