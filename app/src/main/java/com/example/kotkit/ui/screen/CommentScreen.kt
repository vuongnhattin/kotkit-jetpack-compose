package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.viewmodel.CommentViewModel
import com.example.kotkit.ui.icon.Send
import com.example.kotkit.ui.utils.DisplayApiResult

//@Composable
//fun CommentScreen(viewModel: CommentViewModel, videoId: Int) {
//    var commentText by remember { mutableStateOf("") }
//    val commentState by remember { derivedStateOf { viewModel.commentState } }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            OutlinedTextField(
//                value = commentText,
//                onValueChange = { commentText = it },
//                label = { Text("Write a comment...") },
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp),
//                maxLines = 3
//            )
//            IconButton(
//                onClick = {
//                    if (commentText.isNotBlank()) {
//                        viewModel.createComment(videoId, commentText)
//                        commentText = ""  // Clear input after sending
//                    }
//                },
//                enabled = commentText.isNotBlank()
//            ) {
//                Icon(
//                    imageVector = Send,
//                    contentDescription = "Send Comment",
//                    tint = if (commentText.isNotBlank()) Color.Blue else Color.Gray
//                )
//            }
//        }
//
//        DisplayApiResult(
//            state = commentState,
//            onSuccess = {
//                Text("Comment added successfully!", color = Color.Green)
//            },
//            onError = {
//                val error = it.code ?: "Unknown error"
//                Text("Error: $error", color = Color.Red)
//            }
//        )
//    }
//}
@Composable
fun CommentScreen(viewModel: CommentViewModel, videoId: Int) {
    var commentText by remember { mutableStateOf("") }
    val comments by remember { derivedStateOf { viewModel.comments } }

    LaunchedEffect(Unit) {
        viewModel.getAllComments(videoId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Display all comments
        LazyColumn {
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
            OutlinedTextField(
                value = commentText,
                onValueChange = { commentText = it },
                label = { Text("Write a comment...") },
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
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = comment.fullName, fontWeight = FontWeight.Bold)
        Text(text = comment.comment)
        Text(text = comment.createdAt, style = MaterialTheme.typography.bodySmall)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}