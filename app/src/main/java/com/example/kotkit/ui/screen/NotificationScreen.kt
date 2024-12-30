package com.example.kotkit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Notification
import com.example.kotkit.data.viewmodel.NotificationViewModel
import com.example.kotkit.ui.common.NotificationList
import com.example.kotkit.ui.common.UserList
import com.example.kotkit.ui.utils.DisplayApiResult

@Composable
fun NotificationScreen(modifier: Modifier = Modifier,  viewModel: NotificationViewModel = hiltViewModel()) {
    val notificationState = viewModel.notificationState
    val notifications = viewModel.notifications

    LaunchedEffect(Unit) {
        viewModel.fetchNotifications() // Fetch notifications khi giao diện được tải
    }
    DisplayApiResult(notificationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            NotificationList(notifications=notifications, viewModel=viewModel)
        }
    }
}
