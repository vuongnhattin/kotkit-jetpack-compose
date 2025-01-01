package com.example.kotkit.data.mock

import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.Notification
import com.example.kotkit.data.model.UserDetails

object NotificationMock {
    var notificationList: ApiResponse<List<Notification>> = ApiResponse(
        data = listOf(
            Notification(
                0, "Long Nguyen",
                "liked your video: First double kills with Yasuo",
                "https://static.vecteezy.com/system/resources/thumbnails/004/899/680/small_2x/beautiful-blonde-woman-with-makeup-avatar-for-a-beauty-salon-illustration-in-the-cartoon-style-vector.jpg",
                "31/12/2024", false),
            Notification(1, "Minh Tan",
                "commented your video: First double kills with Yasuo\n\n Content: \"Yeah haha, I make sure that's one of the most beautiful moments in your life. I used to pick Yasuo for bottom positions lol.\"",
                "https://img.freepik.com/photos-premium/elevez-votre-marque-avatar-amical-qui-reflete-professionnalisme-ideal-pour-directeurs-ventes_1283595-18531.jpg?semt=ais_hybrid",
                "31/12/2024", false),
            Notification(2, "Nhat Tin",
                "sent you a friend request",
                "",
                "01/01/2025",
                true),
            Notification(3, "Caitlyn",
                "commented your video: My pretty puppies :) \n\n Content: \"They're pretty. So, could you give me some one :>?\"",
                "https://img.freepik.com/premium-vector/man-women-different-avatars-illustration-vector-art-design_666656-112.jpg",
                "30/12/2024",
                false),
        )
    )
}

