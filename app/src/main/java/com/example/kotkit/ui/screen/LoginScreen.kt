package com.example.kotkit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.ui.common.CustomButton
import com.example.kotkit.ui.common.CustomTextField

data class UserLogin(val name: String = "", val token: String = "")

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
//            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Đăng nhập",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Lỗi",
                    color = MaterialTheme.colorScheme.error)
            }
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
            )
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Mật khẩu",
            )


            CustomButton(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth(),
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
                authViewModel.login(user.token)
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