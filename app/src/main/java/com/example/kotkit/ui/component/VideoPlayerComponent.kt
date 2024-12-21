package com.example.kotkit.presentation.components

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
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.kotkit.data.model.Video

@Composable
fun VideoPlayerComponent(
    video: Video,
    exoPlayer: ExoPlayer,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    LaunchedEffect(video) {
        exoPlayer.setMediaItem(MediaItem.fromUri(video.videoUrl))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

    }

    Box(modifier = modifier.fillMaxSize()) {
        // Video Player
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    this.player = exoPlayer
                    useController = false
                    exoPlayer.setMediaItem(MediaItem.fromUri(video.videoUrl))
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