package com.example.kotkit.ui.screen

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayer(videoUri: Uri) {
    // Access the context using LocalContext
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri)) // Set the video source
            prepare() // Prepare the player
            playWhenReady = true // Start playback when ready
        }
    }

    // Use AndroidView to render the PlayerView
    Box(
        modifier = Modifier
            .fillMaxSize() // Match the width of the parent
//            .aspectRatio(16 / 9f) // Maintain a 16:9 aspect ratio
    ) {
        AndroidView(
            factory = { PlayerView(context).apply { player = exoPlayer } },
            modifier = Modifier.fillMaxSize()
        )
    }

    // Release ExoPlayer resources when this composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}