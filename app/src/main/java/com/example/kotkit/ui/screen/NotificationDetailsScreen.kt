package com.example.kotkit.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotkit.data.mock.NotificationMock
import com.example.kotkit.data.model.Notification
import com.example.kotkit.data.viewmodel.NotificationViewModel
import com.example.kotkit.ui.utils.FormatUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDetailScreen(notificationId: Int, navController: NavController) {
    val viewModel: NotificationViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.fetchNotifications() // Fetch notifications khi giao diện được tải
    }
    val notifications by remember { derivedStateOf { viewModel.notifications } }

    val notification = notifications.find { it.notificationID == notificationId }
    Log.i("NotificationDetailScreen", notification.toString())
    Log.i("NotificationDetailScreen", notifications.toString())
    val notificationID = notificationId
    NotificationMock.notificationList.data?.get(notificationID)?.isChecked = true
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết thông báo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (notification != null) {
                AsyncImage(
                    model = notification.avatarUrl,
                    contentDescription = "Avatar of ${notification.sender}",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (notification != null) {
                Text(
                    text = notification.sender,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (notification != null) {
                Text(
                    text = notification.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (notification != null) {
                Text(
                    text = "Sent at: ${notification.createdAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}


//@Composable
//fun NotificationDetailScreen(
//    notification: Notification,
//    onBackClick: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Notification Detail") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            AsyncImage(
//                model = notification.avatarUrl,
//                contentDescription = "Avatar of ${notification.sender}",
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape)
//                    .background(Color.Gray),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = notification.sender,
//                style = MaterialTheme.typography.bodySmall,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = notification.content,
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Sent at: ${notification.createdAt}",
//                style = MaterialTheme.typography.bodySmall,
//                color = Color.Gray
//            )
//        }
//    }
//}
