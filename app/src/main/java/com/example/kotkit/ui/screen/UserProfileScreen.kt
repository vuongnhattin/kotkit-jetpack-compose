package com.example.kotkit.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.LocalUserViewModel
import com.example.kotkit.ui.common.CommonProfileScreen
import com.example.kotkit.ui.utils.DisplayApiResult

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    userId: Int,
) {
    val userViewModel = LocalUserViewModel.current
    val navController = LocalNavController.current
    println("userId, meId: $userId, ${userViewModel.me.userId}")
    if (userViewModel.me.userId == userId) {
        navController.navigate("profile")
    } else {
        CommonProfileScreen(userId = userId)
    }
}

