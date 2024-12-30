package com.example.kotkit.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kotkit.ui.common.CommonProfileScreen

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    userId: Int,
) {
    CommonProfileScreen(userId = userId)
}

