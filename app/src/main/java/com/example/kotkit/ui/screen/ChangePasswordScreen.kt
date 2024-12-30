package com.example.kotkit.ui.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.LocalUserViewModel
import com.example.kotkit.data.dto.input.UpdateInfoInput
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.ui.constant.topAppBarTitleStyle
import com.example.kotkit.ui.icon.Edit_calendar
import com.example.kotkit.ui.utils.DisplayApiResult
import com.example.kotkit.ui.utils.ErrorSnackBar
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val authViewModel = LocalAuthViewModel.current

    val changePasswordState = authViewModel.changePasswordResponse

    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var oldPasswordError by remember { mutableStateOf("") }
    var newPasswordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Đổi mật khẩu", style = topAppBarTitleStyle()
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }, actions = {
            TextButton(onClick = {
                var hasError = false
                if (confirmPassword != newPassword) {
                    confirmPasswordError = "Mật khẩu xác nhận không khớp"
                    hasError = true
                }

                val errorString = "Không được để trống"
                if (oldPassword.isEmpty()) {
                    oldPasswordError = errorString
                    hasError = true
                }
                if (newPassword.isEmpty()) {
                    newPasswordError = errorString
                    hasError = true
                }
                if (confirmPassword.isEmpty()) {
                    confirmPasswordError = errorString
                    hasError = true
                }

                if (hasError) return@TextButton

                authViewModel.changePassword(
                    oldPassword, newPassword
                )
            }) {
                Text("Lưu")
            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                OutlinedTextField(oldPassword,
                    onValueChange = { oldPassword = it },
                    label = { Text("Mật khẩu cũ") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    supportingText = { Text(oldPasswordError) },
                    isError = oldPasswordError.isNotEmpty(),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.hasFocus) {
                                authViewModel.resetChangePasswordState()
                                oldPasswordError = ""
                            }
                        })

                OutlinedTextField(newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Mật khẩu mới") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    supportingText = { Text(newPasswordError) },
                    isError = newPasswordError.isNotEmpty(),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.hasFocus) {
                                authViewModel.resetChangePasswordState()
                                newPasswordError = ""
                            }
                        })

                OutlinedTextField(confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Xác nhận mật khẩu") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    supportingText = { Text(confirmPasswordError) },
                    isError = confirmPasswordError.isNotEmpty(),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.hasFocus) {
                                authViewModel.resetChangePasswordState()
                                confirmPasswordError = ""
                            }
                        })
            }
        }
    }

    DisplayApiResult(
        state = changePasswordState,
        onError = { error ->
            when (error.code) {
                "WRONG_PASSWORD" -> oldPasswordError = "Mật khẩu cũ không đúng"
                else -> ErrorSnackBar("Lỗi không xác định")
            }
        }
    ) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Thông báo") },
            text = { Text("Đổi mật khẩu thành công") },
            confirmButton = {
                Button(onClick = {
                    authViewModel.resetChangePasswordState()
                    navController.popBackStack()
                }) {
                    Text("Đồng ý")
                }
            }
        )
    }
}