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
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.viewmodel.AuthViewModel

data class UserLogin(val name: String = "", val token: String = "")

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val authViewModel = LocalAuthViewModel.current
    val navController = LocalNavController.current

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
                token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmhraWV0MUBnbWFpbC5jb20iLCJpYXQiOjE3MzQ5MzA5MTksImV4cCI6MTc0NDkzMDkxOX0.C9N8oA46F-8aCLgJmm66T2lZ5--XP6VZFYFK_1EIVyw"
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