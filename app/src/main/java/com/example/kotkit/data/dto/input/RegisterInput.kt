package com.example.kotkit.data.dto.input

data class RegisterInput(
    val email: String,
    val fullName: String,
    val password: String,
    val birthday: String,
    val gender: String
)
