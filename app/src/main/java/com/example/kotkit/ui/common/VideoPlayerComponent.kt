package com.example.kotkit.presentation.components

import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.AspectRatioFrameLayout
import com.example.kotkit.data.api.BASE_URL
import com.example.kotkit.data.api.BASE_URL_MINIO
import com.example.kotkit.data.model.Video
import com.example.kotkit.ui.icon.Comment
import com.example.kotkit.ui.icon.DotsHorizontal
import com.example.kotkit.ui.icon.Heart
import com.example.kotkit.ui.icon.PersonCircle
import com.example.kotkit.ui.icon.Save
import com.example.kotkit.ui.icon.Share

// icon tim va so tim, icon comment va so comment...
@Composable
private fun ActionComponent(
    icon: ImageVector,
    count: Int? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )

        if (count != null) {
            Text(
                text = formatCount(count),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

private fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000.0)
        count >= 1_000 -> String.format("%.1fK", count / 1_000.0)
        else -> count.toString()
    }
}

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
            Log.i("VideoPlayerComponent", "video url ${formatVideoUrl(video.videoUrl)}")
            exoPlayer.setMediaItem(MediaItem.fromUri(formatVideoUrl(video.videoUrl)))
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
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Avatar
            ActionComponent(
                icon = PersonCircle,
                onClick = { }
            )

            // React Button
            ActionComponent(
                icon = Heart,
                count = video.numberOfLikes,
                onClick = { }
            )

            // Comment Button
            ActionComponent(
                icon = Comment,
                count = video.numberOfComments,
                onClick = { }
            )

            // Share Button
            ActionComponent(
                icon = Save,
                onClick = { }
            )

            // Save Button
            ActionComponent(
                icon = Share,
                onClick = { }
            )

            // Three dots
            ActionComponent(
                icon = DotsHorizontal,
                onClick = { }
            )
        }
    }
}

private fun formatVideoUrl(videoUrl: String) : String {
    return BASE_URL_MINIO + videoUrl;
}