package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotkit.LocalAuthViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val authViewModel = LocalAuthViewModel.current
//    val navController = LocalNavController.current

    Column() {
        Text("Profile Screen")
        Text("Username: ${authViewModel.getEmailOfMe()}")

        Button(onClick = {
            authViewModel.logout()
        }) {
            Text("Logout")
        }
    }


}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}