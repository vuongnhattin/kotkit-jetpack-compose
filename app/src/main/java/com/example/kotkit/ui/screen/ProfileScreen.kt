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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val authViewModel = LocalAuthViewModel.current
//    val navController = LocalNavController.current

    Column() {
        Text("Profile Screen")
        Text("Username: ${authViewModel.getCurrentUsername()}")

        Button(onClick = {
            authViewModel.logout()
        }) {
            Text("Logout")
        }

        Button(onClick = {
            authViewModel.expireToken()
        }) {
            Text("Test expire token")
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}