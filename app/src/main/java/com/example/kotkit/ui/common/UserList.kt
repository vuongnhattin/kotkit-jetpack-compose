package com.example.kotkit.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.viewmodel.AuthViewModel

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    users: List<UserDetails>,
    navController: NavController
) {
    LazyColumn() {
        items(users) { user ->
            UserResultItem(user = user, navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun UserResultItem(modifier: Modifier = Modifier, user: UserDetails, navController: NavController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    println("Current username: ${authViewModel.getCurrentUsername()}")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (authViewModel.getCurrentUsername() == user.username) {
                    navController.navigate("profile")
                } else {
                    navController.navigate("user-profile/${user.id}")
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    user.fullName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    user.username,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
        FriendshipButton(friendshipStatus = user.friendStatus)
    }
}