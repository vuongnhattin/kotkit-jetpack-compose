package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun ChatScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column() {
        Text("Chat Screen")
    }
}