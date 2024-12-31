package com.example.kotkit.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kotkit.data.model.Notification
import com.example.kotkit.data.viewmodel.NotificationViewModel

@Composable
fun NotificationList(
    modifier: Modifier = Modifier,
    notifications: List<Notification>,
    viewModel: NotificationViewModel
){
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(notifications) { notification ->
            NotificationItem(
                notification = notification,
                onClick = { notificationID ->
                    viewModel.markNotificationAsChecked(notificationID)
                }
            )
        }
    }
}

@Composable
fun NotificationItem(notification: Notification, onClick: (Int) -> Unit) {
    val backgroundColor = if (!notification.isChecked) Color(0xFFCFCFCF) else Color(0xFFEFEFEF)
    val dotColor = if (!notification.isChecked) Color(0xFFFF4081) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(notification.notificationID) }
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(dotColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = notification.content,
            color = Color.Black
        )
    }
}
