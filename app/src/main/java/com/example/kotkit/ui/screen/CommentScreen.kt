package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.viewmodel.CommentViewModel
import com.example.kotkit.ui.screen.utils.DisplayApiResult

@Composable
fun CommentScreen(viewModel: CommentViewModel, videoId: Int) {
    var commentText by remember { mutableStateOf("") }
    val commentState by remember { derivedStateOf { viewModel.commentState } }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            label = { Text("Add a Comment") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (commentText.isNotBlank()) {
                    viewModel.createComment(videoId, commentText)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        DisplayApiResult(
            state = commentState,
            onSuccess = {
                Text("Comment added successfully!", color = Color.Green)
            },
            onError = {
                val error = it.code ?: "Unknown error"
                Text("Error: $error", color = Color.Red)
            }
        )
    }
}