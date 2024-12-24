package com.example.kotkit.data.model

enum class VideoMode {
    PUBLIC, FRIEND, PRIVATE;

    fun getDisplayName(): String = when (this) {
        PUBLIC -> "Công khai"
        FRIEND -> "Bạn bè"
        PRIVATE -> "Riêng tư"
    }
}

data class Video (
    val videoId: Int = 0,
    val title: String = "",
    val videoUrl: String = "",
    val thumbnail: String = "",
    val numberOfLikes: Int = 0,
    val numberOfComments: Int = 0,
    val numberOfViews: Int = 0,
    val creator: UserDetails = UserDetails(),
    val mode: VideoMode = VideoMode.PUBLIC,
    val createdAt: String = "",
    val updatedAt: String = "",
)