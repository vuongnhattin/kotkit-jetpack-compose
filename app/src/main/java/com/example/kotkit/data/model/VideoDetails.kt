package com.example.kotkit.data.model

class VideoDetails (
    val videoId: Int = 0,
    val title: String = "",
    val videoUrl: String = "",
    val thumbnail: String = "",
    val numberOfViews: Int = 0,
    val numberOfLikes: Int = 0,
    val numberOfComments: Int = 0,
    val creatorId: Int = 0,
    val mode: VideoMode = VideoMode.PUBLIC,
    val createdAt: String = "",
    val updatedAt: String = "",
    val avatar: String = "",
    val fullName: String = "",
)