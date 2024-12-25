package com.example.kotkit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.kotkit.ui.icon.Chat_bubble

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    var showComments by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Video content goes here

        // Button to show comments
        IconButton(
            onClick = { showComments = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Chat_bubble, contentDescription = "Show Comments")
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
                    videoId = 1,
                    onClose = { showComments = false }
                )
            }
        }
    }
}