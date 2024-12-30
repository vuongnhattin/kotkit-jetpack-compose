package com.example.kotkit.data.dto.input

import com.example.kotkit.data.model.Gender

data class UpdateInfoInput (
    val fullName: String,
    val birthday: String,
    val gender: String
)