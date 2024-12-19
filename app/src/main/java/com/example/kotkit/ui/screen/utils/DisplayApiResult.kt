package com.example.kotkit.ui.screen.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kotkit.data.model.ApiState

@Composable
fun <T> DisplayApiResult(
    state: ApiState<T>, onLoading: @Composable () -> Unit = {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }, onError: @Composable (ApiState.Error<T>) -> Unit = {
        ErrorSnackBar(it.code)
    }, onSuccess: @Composable (ApiState.Success<T>) -> Unit
) {
    when (state) {
        is ApiState.Loading -> {
            onLoading()
        }

        is ApiState.Error -> {
            // Errors that must be handled in every api call, such as token expired
            when (state.code) {
                "TOKEN_EXPIRED" -> HandleTokenExpired()
                "TIMEOUT", "CONNECT_ERROR" -> ErrorSnackBar("Lỗi kết nối, vui lòng kiểm tra lại kết nối Internet")

                else -> onError(state) // Handle other errors
            }
        }

        is ApiState.Success -> {
            onSuccess(state)
        }
    }
}