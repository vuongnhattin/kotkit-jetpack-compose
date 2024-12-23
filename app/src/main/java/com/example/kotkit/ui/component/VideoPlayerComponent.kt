package com.example.kotkit.presentation.components

import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.AspectRatioFrameLayout
import com.example.kotkit.data.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerComponent(
    video: Video,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    // ExoPlayer setup
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setLoadControl(
                DefaultLoadControl.Builder()
                    .setBufferDurationsMs(
                        30000, // Min buffer
                        60000, // Max buffer
                        2000,  // Buffer for playback
                        3000   // Buffer for playback after rebuffer
                    )
                    .build()
            )
            .build()
            .apply {
                repeatMode = ExoPlayer.REPEAT_MODE_ONE
            }
    }

    DisposableEffect(Unit) {
        Log.i("Tan", "Dispose")
        onDispose {
            exoPlayer.clearVideoSurface()
            exoPlayer.stop()
            exoPlayer.clearMediaItems()
            exoPlayer.clearVideoSurface()
        }
    }

    LaunchedEffect(Unit) {
        Log.i("Tan", "Lauch")
        try {
            exoPlayer.setMediaItem(MediaItem.fromUri(video.videoUrl))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } catch (e: Exception) {
            Log.i("VideoPlayerComponent", "Error loading video: ${e.message}")
        }

    }

    Box(modifier = modifier.fillMaxSize()) {
        // Video Player
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Video Info
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${video.numberOfLikes} likes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${video.numberOfComments} comments",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}