package com.example.kotkit.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kotkit.data.model.FriendshipStatus

data class FriendshipInfo(
    val text: String = "",
    val action: () -> Unit = {}
) {
    companion object {
        fun getFriendshipInfo(friendshipStatus: FriendshipStatus?): FriendshipInfo {
            when (friendshipStatus) {
                null -> {
                    return FriendshipInfo("Kết bạn") {
                        println("Kết bạn")
                    }
                }

                FriendshipStatus.SENT -> {
                    return FriendshipInfo("Huỷ lời mời") {
                        println("Huỷ lời mời")
                    }
                }

                FriendshipStatus.RECEIVED -> {
                    return FriendshipInfo("Phản hồi") {
                        println("Phản hồi")
                    }
                }

                FriendshipStatus.FRIEND -> {
                    return FriendshipInfo("Huỷ kết bạn") {
                        println("Huỷ kết bạn")
                    }
                }
            }
        }
    }
}

@Composable
fun FriendshipButton(modifier: Modifier = Modifier, friendshipStatus: FriendshipStatus?) {
    val friendshipInfo = FriendshipInfo.getFriendshipInfo(friendshipStatus)

    // Get colors from MaterialTheme
    val backgroundColor = when (friendshipStatus) {
        null -> MaterialTheme.colorScheme.primary
        FriendshipStatus.SENT -> MaterialTheme.colorScheme.secondary
        FriendshipStatus.RECEIVED -> MaterialTheme.colorScheme.secondary
        FriendshipStatus.FRIEND -> MaterialTheme.colorScheme.secondary
    }

    val contentColor = when (friendshipStatus) {
        null -> MaterialTheme.colorScheme.onPrimary
        FriendshipStatus.SENT -> MaterialTheme.colorScheme.onSecondary
        FriendshipStatus.RECEIVED -> MaterialTheme.colorScheme.onSecondary
        FriendshipStatus.FRIEND -> MaterialTheme.colorScheme.onSurface
    }

    // Button with dynamic colors
    Button(
        onClick = friendshipInfo.action,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(friendshipInfo.text)
    }
}

