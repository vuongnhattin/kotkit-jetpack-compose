package com.example.kotkit.data.model

enum class VideoVisibility {
    PUBLIC,
    PRIVATE
}

data class Video (
    val id: Int = 0,
    val title: String = "",
    val videoUrl: String = "",
    val thumbnail: String = "",
    val numberOfLikes: Int = 0,
    val numberOfComments: Int = 0,
    val numberOfViews: Int = 0,
    val creator: UserDetails = UserDetails(),
    val visibility: VideoVisibility = VideoVisibility.PUBLIC,
    val createdAt: String = "",
    val updatedAt: String = "",
)