package com.example.kotkit.data.dto.response

data class LoginResponse (
    val token: String,
    val expiresIn: Long,
)