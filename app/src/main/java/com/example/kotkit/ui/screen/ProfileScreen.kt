package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotkit.data.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    Column() {
        Text("Profile Screen")
        Text("Username: ${authViewModel.getCurrentUsername()}")
        Button(onClick = {
            authViewModel.logout()
        }) {
            Text("Logout")
        }
    }
}