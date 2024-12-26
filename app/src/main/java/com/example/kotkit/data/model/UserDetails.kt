package com.example.kotkit.data.model

enum class FriendshipStatus {
    SENT,
    RECEIVED,
    FRIEND
}

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

data class UserDetails(
    val userId: Int = 0,
    val email: String = "",
    val fullName: String = "",
    val roles: String = "",
    val avatar: String? = null,
    val birthday: String = "",
    val gender: Gender = Gender.MALE,
    val numberOfFriends: Int = 0,
    val numberOfVideos: Int = 0,
    val isBlock: Boolean = false,
    val isVerified: Boolean = false,
    var friendStatus: FriendshipStatus? = null,
)
