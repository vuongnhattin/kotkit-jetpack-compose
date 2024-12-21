package com.example.kotkit.data.model


data class Comment(
    val commentId: Int = 0,
    val authorId: Int = 0,
    val videoId: Int = 0,
    val avatar: String? = null,
    val comment: String = "",
    val fullName: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)