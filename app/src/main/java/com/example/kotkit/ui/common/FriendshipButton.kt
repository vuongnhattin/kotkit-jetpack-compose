package com.example.kotkit.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotkit.LocalUserViewModel
import com.example.kotkit.data.model.FriendshipStatus
import com.example.kotkit.data.viewmodel.FriendViewModel
import com.example.kotkit.data.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendshipButton(modifier: Modifier = Modifier, friendshipStatus: FriendshipStatus?, userId: Int) {
    val userViewModel: UserViewModel = LocalUserViewModel.current
    val friendViewModel: FriendViewModel = hiltViewModel()
    var isReplyOpen by remember { mutableStateOf(false) }
    var isUnfriendOpen by remember { mutableStateOf(false) }

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

    val text = when (friendshipStatus) {
        null -> "Kết bạn"
        FriendshipStatus.SENT -> "Huỷ lời mời"
        FriendshipStatus.RECEIVED -> "Phản hồi"
        FriendshipStatus.FRIEND -> "Bạn bè"
    }

    // Button with dynamic colors
    CustomButton(
        onClick = {
            println("cccc")
            when (friendshipStatus) {
                null -> {
                    friendViewModel.sendFriendRequest(userId)
                    userViewModel.updateFriendStatus(userId, FriendshipStatus.SENT)
                }
                FriendshipStatus.SENT -> {
                    friendViewModel.takeBackFriendRequest(userId)
                    userViewModel.updateFriendStatus(userId, null)
                }
                FriendshipStatus.RECEIVED -> {
                    isReplyOpen = true
                }
                FriendshipStatus.FRIEND -> {
                    isUnfriendOpen = true
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
    ) {
        Text(text)
    }

    val replyOptions = listOf("Chấp nhận", "Từ chối")
    CustomModalBottomSheet(
        isSheetOpen = isReplyOpen,
        onDismissRequest = { isReplyOpen = false },
        options = replyOptions
        ) { selectedOption ->
        if (selectedOption == "Chấp nhận") {
            friendViewModel.acceptFriendRequest(userId)
            userViewModel.updateFriendStatus(userId, FriendshipStatus.FRIEND)
        } else {
            friendViewModel.rejectFriendRequest(userId)
            userViewModel.updateFriendStatus(userId, null)
        }
    }

    val unFriendOption = listOf("Huỷ kết bạn")
    CustomModalBottomSheet(
        isSheetOpen = isUnfriendOpen,
        onDismissRequest = { isUnfriendOpen = false },
        options = unFriendOption
    ) {
        friendViewModel.unfriend(userId)
        userViewModel.updateFriendStatus(userId, null)
    }
}

