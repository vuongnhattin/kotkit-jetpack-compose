package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.AuthApiService
import com.example.kotkit.data.dto.input.ChangePasswordInput
import com.example.kotkit.data.dto.input.LoginInput
import com.example.kotkit.data.dto.input.RegisterInput
import com.example.kotkit.data.localstorage.TokenManager
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.dto.response.LoginResponse
import com.example.kotkit.data.model.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApiService: AuthApiService,
) : ViewModel() {
    var isAuthenticated by mutableStateOf(tokenManager.isAuthenticated())
        private set



    var loginResponse by mutableStateOf<ApiState<LoginResponse>>(ApiState.Empty())
    var registerResponse by mutableStateOf<ApiState<Void>>(ApiState.Empty())
    var changePasswordResponse by mutableStateOf<ApiState<Void>>(ApiState.Empty())



    fun changePassword(oldPassword: String, newPassword: String) {
        fetchApi({ changePasswordResponse = it }) {
            authApiService.changePassword(ChangePasswordInput(oldPassword, newPassword))
        }
    }

    fun resetChangePasswordState() {
        changePasswordResponse = ApiState.Empty()
    }

    fun getEmailOfMe(): String {
        return tokenManager.getCurrentUsername()
    }

    fun getMeId(): Int {
        return tokenManager.getMeId()
    }

    fun logout() {
        tokenManager.clearToken()
        isAuthenticated = false
        loginResponse = ApiState.Empty()
    }

    fun mockLogin(token: String) {
        tokenManager.saveToken(token)
        isAuthenticated = true
    }

    fun login(email: String, password: String) {
        fetchApi({ loginResponse = it }) {
            val response = authApiService.login(LoginInput(email, password))
            tokenManager.saveToken(response.data!!.token)
            isAuthenticated = true
            response
        }
    }

    fun resetLoginState() {
        loginResponse = ApiState.Empty()
    }

    fun resetRegisterState() {
        registerResponse = ApiState.Empty()
    }

    fun register(email: String, fullName: String, password: String, birthday: String, gender: String) {
        fetchApi({registerResponse = it}) {
            val input = RegisterInput(email, fullName, password, birthday, gender)
            println("register input: $input")
            authApiService.register(input)
        }
    }
}