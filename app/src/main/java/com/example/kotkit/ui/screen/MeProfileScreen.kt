package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.LocalUserViewModel
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.ui.common.CommonProfileScreen
import com.example.kotkit.ui.constant.topAppBarTitleStyle
import com.example.kotkit.ui.icon.Settings
import com.example.kotkit.ui.utils.DisplayApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeProfileTopBar(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val authViewModel = LocalAuthViewModel.current
    val userViewModel = LocalUserViewModel.current
    val meState = userViewModel.userDetails
    LaunchedEffect(Unit) { userViewModel.getMe() }
    DisplayApiResult(state = meState, onLoading = {}) { state ->
        val me = state.data ?: UserDetails()
        CenterAlignedTopAppBar(
            title = {
                Text(
                   me.fullName, style = topAppBarTitleStyle()
                )

            },
            actions = {
                IconButton(onClick = {
                    navController.navigate("setting")
                }) {
                    Icon(Settings, contentDescription = null)
                }
            },
        )
    }
}

@Composable
fun MeProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CommonProfileScreen(isMe = true)
    }
}