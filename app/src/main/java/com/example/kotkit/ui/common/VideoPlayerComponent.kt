package com.example.kotkit.presentation.components

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.kotkit.LocalVideoViewModel
import com.example.kotkit.data.api.BASE_URL_MINIO
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.viewmodel.UploadVideoViewModel
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.ui.icon.Bookmark_filled
import com.example.kotkit.ui.icon.Comment
import com.example.kotkit.ui.icon.DotsHorizontal
import com.example.kotkit.ui.icon.Heart
import com.example.kotkit.ui.icon.PersonCircle
import com.example.kotkit.ui.icon.Save
import com.example.kotkit.ui.icon.Share
import com.example.kotkit.ui.screen.CommentScreen
import com.example.kotkit.ui.utils.FormatUtils.formatNumber
import com.example.kotkit.ui.utils.FormatUtils.formatVideoUrl

@Composable
private fun ActionComponent(
    icon: ImageVector,
    count: Int? = null,
    onClick: () -> Unit,
    liked: Boolean = false,
    saved: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (saved) Color.Yellow else if (liked) Color.Red else Color.White,
            modifier = Modifier.size(32.dp)
        )

        if (count != null) {
            Text(
                text = formatNumber(count),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerComponent(
    video: Video,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val videoViewModel = LocalVideoViewModel.current
    var showComments by remember { mutableStateOf(false) }

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
        Log.i("Tan", "${formatVideoUrl(video.videoUrl)}")
        try {
            exoPlayer.setMediaItem(MediaItem.fromUri(formatVideoUrl(video.videoUrl)))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } catch (e: Exception) {
            Log.i("VideoPlayerComponent", "Error loading video: ${e.message}")
        }
    }

    Box(modifier = modifier
        .background(Color.Black)
        .fillMaxSize()
    ) {
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

        // Comment component displayed from the bottom
        if (showComments) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f) // Half of the screen
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ) {
                CommentScreen(
                    viewModel = hiltViewModel(),
                    videoId = video.videoId,
                    onClose = { showComments = false }
                )
            }
        }

        // Action buttons column
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Avatar
            ActionComponent(
                icon = PersonCircle,
                onClick = { },
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // React Button
            ActionComponent(
                icon = Heart,
                count = video.numberOfLikes,
                liked = videoViewModel.isVideoLiked(video.videoId),
                onClick = {
                    videoViewModel.updateLikeVideoState(video.videoId)
                }
            )

            // Comment Button
            ActionComponent(
                icon = Comment,
                count = video.numberOfComments,
                onClick = {
                    showComments = true
                }
            )

            // Save Button
            ActionComponent(
                icon = Bookmark_filled,
                saved = videoViewModel.isVideoSaved(video.videoId),
                onClick = {
                    videoViewModel.updateSaveVideoState(video.videoId)
                }
            )

            // Share Button
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