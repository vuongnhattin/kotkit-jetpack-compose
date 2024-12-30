package com.example.kotkit.data.mock

import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.Notification
import com.example.kotkit.data.model.UserDetails

object NotificationMock {
    val notificationList: ApiResponse<List<Notification>> = ApiResponse(
        data = listOf(
            Notification(1, "Kiet liked your video", false),
            Notification(2, "Tan commented your video ", false),
            Notification(3, "Tin saved your video", false),
            Notification(4, "Long deleted your video", false)
        )
    )
}

