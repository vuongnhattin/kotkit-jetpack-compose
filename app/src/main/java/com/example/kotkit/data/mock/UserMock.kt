package com.example.kotkit.data.mock

import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.FriendshipStatus
import com.example.kotkit.data.model.UserDetails

object UserMock {
    val users: ApiResponse<List<UserDetails>> = ApiResponse(
        listOf(
            UserDetails(
                id = 1,
                username = "tin1",
                fullName = "Tín 1",
                avatar = "https://scontent.fsgn10-1.fna.fbcdn.net/v/t39.30808-6/461662687_1900580623784458_2552747995351221907_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeEuvScguBciXXeNfZFV24A14hdj2-KjnUfiF2Pb4qOdR8fH4bPRxqU1qRorZ1HZOPq6VxGxQh0BMRugD1bJfHiV&_nc_ohc=NUu5u4RP1PIQ7kNvgHdjBqQ&_nc_zt=23&_nc_ht=scontent.fsgn10-1.fna&_nc_gid=AMWELK3KlkVEPz2MQGOpa_c&oh=00_AYD5_-ehbRIq8_MWhAPGxW6J7i4bmdjsZ0FZGhUkeb-pgQ&oe=675CF2FD",
                birthday = "1990-01-01",
                numberOfFriends = 100,
                friendStatus = null
            ),
            UserDetails(
                id = 2,
                username = "tin2",
                fullName = "Tín 2",
                avatar = "https://example.com/avatar.jpg",
                birthday = "1990-01-01",
                numberOfFriends = 100,
                friendStatus = FriendshipStatus.FRIEND
            ),
            UserDetails(
                id = 3,
                username = "tin3",
                fullName = "Tín 3",
                avatar = "https://example.com/avatar.jpg",
                birthday = "1990-01-01",
                numberOfFriends = 100,
                friendStatus = FriendshipStatus.SENT
            ),
            UserDetails(
                id = 4,
                username = "tin4",
                fullName = "Tín 4",
                avatar = "https://example.com/avatar.jpg",
                birthday = "1990-01-01",
                numberOfFriends = 100,
                friendStatus = FriendshipStatus.RECEIVED
            )
        )
    )
}
