package com.example.kotkit.ui.screen

import android.graphics.Outline
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.viewmodel.AuthViewModel
import com.example.kotkit.ui.common.CustomButton
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.common.LoginRegisterLayout
import com.example.kotkit.ui.utils.DisplayApiResult
import kotlin.math.log


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val navController = LocalNavController.current
    val authViewModel = LocalAuthViewModel.current

    val loginState = authViewModel.loginResponse
    val isLoading = loginState is ApiState.Loading

    DisplayApiResult(
        state = loginState,
        onLoading = { },
        onError = { error ->
            when (error.code) {
                "VALIDATION_ERROR" -> error.data?.let { data ->
                    val dataMap = data as Map<String, String>
                    emailError = when (dataMap["email"]) {
                        "EMAIL_REQUIRED" -> "Email không được để trống"
                        else -> ""
                    }
                    passwordError = when (dataMap["password"]) {
                        "PASSWORD_REQUIRED" -> "Mật khẩu không được để trống"
                        else -> ""
                    }
                }

                "EMAIL_NOT_FOUND" -> emailError = "Email không tồn tại"
                "WRONG_PASSWORD" -> passwordError = "Mật khẩu không đúng"
                else -> {}
            }
        }
    ) {
        navController.navigate("home")
    }

    LoginRegisterLayout(
        header = "Đăng nhập",
        footer = {
            Text("Bạn không có tài khoản?")
            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text("Đăng ký")
            }
        }
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            maxLines = 1,
            supportingText = { Text(emailError) },
            isError = emailError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().onFocusChanged {
                if (it.hasFocus) {
                    authViewModel.resetLoginState()
                    emailError = ""
                }
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus() // Clears focus to avoid looping
                    authViewModel.login(email.text, password.text) // Trigger login
                }
            ),
            maxLines = 1,
            supportingText = { Text(passwordError) },
            isError = passwordError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().onFocusChanged { focusState ->
                if (focusState.hasFocus) {
                    authViewModel.resetLoginState()
                    passwordError = ""
                }
            },
            visualTransformation = PasswordVisualTransformation()
        )

        CustomButton(
            onClick = {
                authViewModel.login(email.text, password.text)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(text = "Đăng nhập")
        }
    }
}