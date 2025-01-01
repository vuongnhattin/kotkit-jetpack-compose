package com.example.kotkit.data.dto.input

import com.example.kotkit.data.model.VideoMode

data class UpdateVideoInput(
    val title: String,
    val mode: VideoMode
)