package com.example.kotkit.data.model

data class Notification(
    val notificationID: Int = 0,
    val sender: String = "",
    val content: String = "",
    val avatarUrl: String = "",
    val createdAt: String = "",
    var isChecked: Boolean
)