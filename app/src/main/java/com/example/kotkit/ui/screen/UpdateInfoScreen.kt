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
fun UpdateInfoScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val authViewModel = LocalAuthViewModel.current
    val userViewModel = LocalUserViewModel.current

    val userState = userViewModel.userDetails
    LaunchedEffect(Unit) {
        userViewModel.getMe()
    }

    val updateInfoState = userViewModel.updateInfoResponse

    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    val genderMap = mapOf(
        "Nam" to "MALE", "Nữ" to "FEMALE", "Khác" to "OTHER"
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

    var fullNameError by remember { mutableStateOf("") }

    var isInitialized by remember { mutableStateOf(false) }

    val loginState = authViewModel.registerResponse
    val isLoading = loginState is ApiState.Loading
    val horizontalSpace = 8.dp

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Sửa hồ sơ", style = topAppBarTitleStyle()
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }, actions = {
            TextButton(onClick = {
                if (fullName.isEmpty()) {
                    fullNameError = "Họ và tên không được để trống"
                    return@TextButton
                }

                userViewModel.updateMe(
                    UpdateInfoInput(
                        fullName = fullName,
                        birthday = birthday,
                        gender = genderMap[gender]!!
                    )
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
            DisplayApiResult(
                state = userState,
            ) { state ->
                val user = state.data!!

                if (!isInitialized) {
                    email = user.email
                    fullName = user.fullName
                    birthday = user.birthday
                    gender = getKeyByValue(genderMap, user.gender.toString()) ?: "Khác"
                    isInitialized = true
                }

                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    OutlinedTextField(
                        email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                        supportingText = { Text("") }
                    )
                    OutlinedTextField(fullName,
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
                            })

                    OutlinedTextField(
                        birthday,
                        onValueChange = { birthday = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { datePickerDialog.show() },
                        readOnly = true,
                        label = {
                            Text(
                                "Ngày sinh", maxLines = 1, overflow = TextOverflow.Ellipsis
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                authViewModel.resetRegisterState()
                                datePickerDialog.show()
                            }) {
                                Icon(Edit_calendar, contentDescription = null)
                            }
                        },
                        maxLines = 1,
                        supportingText = { Text("") }
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
//            .weight(1f)
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
                                    genderExpanded = !genderExpanded
                                }) {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            supportingText = { Text("") }
                        )
                        DropdownMenu(expanded = genderExpanded,
                            onDismissRequest = { genderExpanded = false }) {
                            genderMap.forEach { (displayText, value) ->
                                DropdownMenuItem(text = { Text(displayText) }, onClick = {
                                    gender = displayText
                                    genderExpanded = false
                                })
                            }
                        }
                    }
                }
            }
        }
    }

    DisplayApiResult(
        state = updateInfoState
    ) {
        userViewModel.resetUpdateInfoState()
        navController.popBackStack()
    }
}

fun getKeyByValue(map: Map<String, String>, value: String): String? {
    return map.entries.find { it.value == value }?.key
}