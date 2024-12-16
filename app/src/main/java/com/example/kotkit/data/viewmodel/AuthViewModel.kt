package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.localstorage.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    var authenticated by mutableStateOf(false)
        private set

    fun getCurrentUsername(): String {
        return tokenManager.getCurrentUsername()
    }

    fun logout() {
        tokenManager.clearToken()
        authenticated = false
    }

    fun login(token: String) {
        tokenManager.saveToken(token)
        authenticated = true
    }
}