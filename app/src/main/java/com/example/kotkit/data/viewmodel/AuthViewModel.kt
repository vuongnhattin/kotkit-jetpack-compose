package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.localstorage.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    var isAuthenticated by mutableStateOf(tokenManager.isAuthenticated())
        private set

    var isTokenExpired by mutableStateOf(false)
        private set

    fun expireToken() {
        isTokenExpired = true
    }

    fun getCurrentUsername(): String {
        return tokenManager.getCurrentUsername()
    }

    fun logout() {
        tokenManager.clearToken()
        isAuthenticated = false
    }

    fun login(token: String) {
        tokenManager.saveToken(token)
        isAuthenticated = true
        isTokenExpired = false
    }
}