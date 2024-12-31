package com.example.kotkit.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.viewmodel.CommentViewModel
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.icon.Send
import com.example.kotkit.ui.utils.DisplayApiResult


@Composable
fun CommentScreen(
    viewModel: CommentViewModel,
    videoId: Int,
    onClose: () -> Unit
) {
    var commentText by remember { mutableStateOf("") }
    val comments by remember { derivedStateOf { viewModel.comments } }
    val listState = rememberLazyListState()
    LaunchedEffect(comments) {
        if (comments.size >= 1) {
            listState.animateScrollToItem(comments.size - 1) // Scroll to the bottom on comments update
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getAllComments(videoId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Close button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onClose() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close Comments")
            }
        }

        // Display comments in a pleasant layout
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }

        // Input field and send button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                shape = RoundedCornerShape(64.dp),
                value = commentText,
                onValueChange = { commentText = it },
                placeholder = { Text("Write a comment...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                maxLines = 3
            )
            IconButton(
                onClick = {
                    if (commentText.isNotBlank()) {
                        viewModel.createComment(videoId, commentText)
                        commentText = ""
                    }
                },
                enabled = commentText.isNotBlank()
            ) {
                Icon(
                    imageVector = Send,
                    contentDescription = "Send Comment",
                    tint = if (commentText.isNotBlank()) Color.Blue else Color.Gray
                )
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile picture placeholder
        AsyncImage(
            model = comment.avatar,
            contentDescription = "Avatar of ${comment.fullName}",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))


        Spacer(modifier = Modifier.width(8.dp))

        Column {
            // Commenter's name
            Text(
                text = comment.fullName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            // Comment text
            Text(
                text = comment.comment,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
            // Time of comment
            Text(
                text = comment.createdAt,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}