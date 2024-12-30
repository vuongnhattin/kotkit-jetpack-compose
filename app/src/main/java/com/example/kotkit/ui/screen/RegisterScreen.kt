package com.example.kotkit.ui.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.ui.common.CustomButton
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.common.LoginRegisterLayout
import com.example.kotkit.ui.icon.Edit_calendar
import com.example.kotkit.ui.utils.DisplayApiResult
import com.example.kotkit.ui.utils.ErrorSnackBar
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val authViewModel = LocalAuthViewModel.current

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordConfirm by remember { mutableStateOf(TextFieldValue("")) }
    var birthday by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    val genderMap = mapOf(
        "Nam" to "MALE",
        "Nữ" to "FEMALE",
        "Khác" to "OTHER"
    )

    // Calendar instance to initialize the date picker
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedMonth = String.format("%02d", selectedMonth + 1)
            val formattedDay = String.format("%02d", selectedDay)
            birthday = "$selectedYear-$formattedMonth-$formattedDay"
        }, year, month, day
    )


    var emailError by remember { mutableStateOf("") }
    var fullNameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordConfirmError by remember { mutableStateOf("") }
    var birthdayError by remember { mutableStateOf("") }
    var genderError by remember { mutableStateOf("") }

    val loginState = authViewModel.registerResponse
    val isLoading = loginState is ApiState.Loading

    DisplayApiResult(
        authViewModel.registerResponse,
        onLoading = {},
        onError = { error ->
            when (error.code) {
                "EMAIL_DUPLICATED" -> emailError = "Email này đã tồn tại"
                else -> {
                    ErrorSnackBar("Lỗi không xác định")
                }
            }
        }
    ) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Đăng ký thành công") },
            text = { Text("Vui lòng đăng nhập để tiếp tục") },
            confirmButton = {
                Button(onClick = {
                    authViewModel.resetRegisterState()
                    navController.navigate("login")
                }) {
                    Text("Đồng ý")
                }
            }
        )
    }

    LoginRegisterLayout(header = "Đăng ký", footer = {
        Text("Đã có tài khoản?")
        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text("Đăng nhập")
        }
    }) {
        val horizontalSpace = 8.dp
        OutlinedTextField(
            email,
            onValueChange = { email = it },
            label = { Text("Email") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            supportingText = { Text(emailError) },
            isError = emailError.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        authViewModel.resetRegisterState()
                        emailError = ""
                    }
                }
        )
        OutlinedTextField(
            fullName,
            onValueChange = { fullName = it },
            label = { Text("Họ và tên") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            supportingText = { Text(fullNameError) },
            isError = fullNameError.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        authViewModel.resetRegisterState()
                        fullNameError = ""
                    }
                }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
        ) {
            OutlinedTextField(
                password,
                onValueChange = { password = it },
                isError = passwordError.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        if (focusState.hasFocus) {
                            authViewModel.resetRegisterState()
                            passwordError = ""
                        }
                    },
                label = { Text("Mật khẩu") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                supportingText = { Text(passwordError) },
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                passwordConfirm,
                onValueChange = { passwordConfirm = it },
                isError = passwordConfirmError.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        if (focusState.hasFocus) {
                            authViewModel.resetRegisterState()
                            passwordConfirmError = ""
                        }
                    },
                label = {
                    Text(
                        "Xác nhận mật khẩu", maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                supportingText = { Text(passwordConfirmError) },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
        ) {
            OutlinedTextField(
                birthday,
                onValueChange = { birthday = it },
                isError = birthdayError.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable { datePickerDialog.show() },
                readOnly = true,
                label = { Text("Ngày sinh", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                trailingIcon = {
                    IconButton(onClick = {
                        authViewModel.resetRegisterState()
                        birthdayError = ""
                        datePickerDialog.show()
                    }) {
                        Icon(Edit_calendar, contentDescription = null)
                    }
                },
                maxLines = 1,
                supportingText = { Text(birthdayError) },
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                OutlinedTextField(
                    gender,
                    onValueChange = { gender = it },
                    label = { Text("Giới tính") },
                    maxLines = 1,
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            authViewModel.resetRegisterState()
                            genderError = ""
                            genderExpanded = !genderExpanded
                        }) {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded)
                        }
                    },
                    supportingText = { Text(genderError) },
                    isError = genderError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false }
                ) {
                    genderMap.forEach { (displayText, value) ->
                        DropdownMenuItem(
                            text = { Text(displayText) },
                            onClick = {
                                gender = displayText
                                genderExpanded = false
                            }
                        )
                    }
                }
            }
        }
        CustomButton(
            onClick = {
                // Validate input
                var hasError = false
                if (password.text != passwordConfirm.text) {
                    passwordConfirmError = "Mật khẩu không khớp"
                    hasError = true
                }

                val fields = listOf(
                    email.text,
                    fullName.text,
                    password.text,
                    passwordConfirm.text,
                    birthday,
                    gender
                )
                val errorString = "Không được trống"
                for (field in fields) {
                    if (field.isEmpty()) {
                        if (field == email.text) emailError = errorString
                        if (field == fullName.text) fullNameError = errorString
                        if (field == password.text) passwordError = errorString
                        if (field == passwordConfirm.text) passwordConfirmError = errorString
                        if (field == birthday) birthdayError = errorString
                        if (field == gender) genderError = errorString

                        hasError = true
                    }
                }

                if (hasError) return@CustomButton

                authViewModel.register(
                    email.text,
                    fullName.text,
                    password.text,
                    birthday,
                    genderMap[gender]!!
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(text = "Đăng ký")
        }
    }
}
