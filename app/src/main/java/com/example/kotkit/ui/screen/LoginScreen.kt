package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.data.viewmodel.AuthViewModel

data class UserLogin(val name: String = "", val token: String = "")

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    val authViewModel = LocalAuthViewModel.current

    if (authViewModel.isAuthenticated) {
        navController.navigate("home")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val users = listOf(
            UserLogin(
                name = "User 1",
                token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aW4iLCJpYXQiOjE3MzMxNjAzMDksImV4cCI6MTc0MzE2MDMwOX0.tn5oD4LrCcUhZ7lv5FDsJxCbGZSNiKxx6UvuZjCuvfw"
//                token = ""
            ),
            UserLogin(
                name = "User 2",
                token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aW4xIiwiaWF0IjoxNzMzMTYwMzM0LCJleHAiOjE3NDMxNjAzMzR9.1aCviyoU1-n5vhqKlri4lfUr6f4czk9Th4XtcmXnTA0"
            ),
            UserLogin(
                name = "User 3",
                token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aW4yIiwiaWF0IjoxNzMzODEyMjczLCJleHAiOjE3NDM4MTIyNzN9.SH2xriwjGKuCq3LUeDDrF8PWhRRnEnqpV-OTA_7b81A"
            ),
        )

        users.forEach { user ->
            Button(onClick = {
                authViewModel.login(user.token)
                navController.navigate("home")
            }) {
                Text(text = user.name)
            }
        }
    }
}