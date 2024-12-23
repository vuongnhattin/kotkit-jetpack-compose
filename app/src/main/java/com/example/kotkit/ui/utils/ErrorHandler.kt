package com.example.kotkit.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.data.model.ApiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private fun <T> deserializeErrorData(data: Any?, typeToken: TypeToken<T>): T {
    val gson = Gson()

    // Convert the data to a JSON string
    val json = gson.toJson(data)

    // Deserialize the JSON string into the provided type
    return gson.fromJson(json, typeToken.type)
}

@Composable
fun HandleTokenExpired() {
    val authViewModel = LocalAuthViewModel.current
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Phiên đăng nhập hết hạn") },
        text = { Text("Phiên đăng nhập của bạn đã hết hạn, vui lòng đăng nhập lại") },
        confirmButton = {
            Button(onClick = {
                authViewModel.logout()
            }) {
                Text("Đăng nhập")
            }
        }
    )
}

@Composable
fun ErrorSnackBar(message: String?) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Show the snackbar when the composable is recomposed
    LaunchedEffect(message) {
        snackbarHostState.showSnackbar(message ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .padding(32.dp)
        ) {
            Snackbar() {
                Text(message ?: "")
            }
        }
    }
}

