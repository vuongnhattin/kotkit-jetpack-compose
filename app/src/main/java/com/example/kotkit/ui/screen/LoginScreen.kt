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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.viewmodel.AuthViewModel
import com.example.kotkit.ui.common.CustomButton
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.utils.DisplayApiResult
import kotlin.math.log

data class UserLogin(val name: String = "", val token: String = "")

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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Đăng nhập",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
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
                modifier = Modifier.onFocusChanged {
                    if (it.hasFocus) {
                        authViewModel.resetLoginState()
                        emailError = ""
                    }
                }
            )

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Mật khẩu") },
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
                modifier = Modifier.onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        authViewModel.resetLoginState()
                        passwordError = ""
                    }
                }
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .navigationBarsPadding()
                    .background(MaterialTheme.colorScheme.secondary),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bạn không có tài khoản?")
                TextButton(onClick = {

                }) {
                    Text("Đăng ký")
                }
            }
        }
    }
}

@Composable
fun MockLoginScreen(modifier: Modifier = Modifier) {
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
                authViewModel.mockLogin(user.token)
                navController.navigate("home")
            }) {
                Text(text = user.name)
            }
        }

        TextButton(
            onClick = {
                navController.navigate("real-login")
            }
        ) {
            Text("Đăng nhập")
        }
    }
}