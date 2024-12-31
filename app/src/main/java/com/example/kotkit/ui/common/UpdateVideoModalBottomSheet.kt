package com.example.kotkit.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.model.VideoMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateVideoModalBottomSheet(
    video: Video,
    onDismiss: () -> Unit,
    isVisible: Boolean,
    onSave: (String, VideoMode) -> Unit,
    modifier: Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    var title by remember { mutableStateOf(video.title) }
    var selectedMode by remember { mutableStateOf(video.mode) }

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(0.8f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Header
                Text(
                    text = "Chỉnh sửa video",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Title Input
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Tiêu đề") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                // Privacy Settings
                Text(
                    text = "Chế độ riêng tư",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column {
                    VideoMode.entries.forEach { privacy ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedMode == privacy,
                                onClick = { selectedMode = privacy }
                            )
                            Text(
                                text = when (privacy) {
                                    VideoMode.PUBLIC -> "Công khai"
                                    VideoMode.PRIVATE -> "Riêng tư"
                                    VideoMode.FRIEND -> "Bạn bè"
                                },
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                // Save Button
                Button(
                    onClick = {
                        onSave(title, selectedMode)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Lưu thay đổi")
                }
            }
        }
    }
}