package com.example.kotkit.data.model

enum class FriendshipStatus {
    SENT,
    RECEIVED,
    FRIEND
}

data class UserDetails(
    val id: Int = 0,
    val username: String = "",
    val fullName: String = "",
    val avatar: String? = null,
    val birthday: String = "",
    val numberOfFriends: Int = 0,
    val friendStatus: FriendshipStatus? = null,
)
