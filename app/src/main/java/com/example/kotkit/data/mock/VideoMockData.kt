// VideoMockData.kt
package com.example.kotkit.data.mock

import com.example.kotkit.data.model.Video
import com.example.kotkit.data.model.VideoVisibility
import com.example.kotkit.data.model.UserDetails

object VideoMockData {
    val videos = listOf(
        Video(
            id = 1,
            title = "Mountain View #nature",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test2",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1200,
            numberOfComments = 300,
            numberOfViews = 5000,
            creator = UserDetails(username = "nature_explorer"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 2,
            title = "Beach Sunset #travel",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test1",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 800,
            numberOfComments = 150,
            numberOfViews = 3000,
            creator = UserDetails(username = "travel_life"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 3,
            title = "City Lights #urban",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test3",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2500,
            numberOfComments = 450,
            numberOfViews = 8000,
            creator = UserDetails(username = "urban_shots"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 4,
            title = "Coffee Time #lifestyle",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test4",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 950,
            numberOfComments = 200,
            numberOfViews = 4500,
            creator = UserDetails(username = "coffee_lover"),
            visibility = VideoVisibility.PRIVATE
        ),
        Video(
            id = 5,
            title = "Dance Cover #dance",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test5",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 3500,
            numberOfComments = 600,
            numberOfViews = 12000,
            creator = UserDetails(username = "dance_queen"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 6,
            title = "Cooking Tutorial #food",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test6",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1800,
            numberOfComments = 350,
            numberOfViews = 7000,
            creator = UserDetails(username = "chef_life"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 7,
            title = "Pet Moments #pets",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test7",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 4200,
            numberOfComments = 750,
            numberOfViews = 15000,
            creator = UserDetails(username = "pet_lover"),
            visibility = VideoVisibility.PRIVATE
        ),
        Video(
            id = 8,
            title = "Workout Routine #fitness",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test8",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2200,
            numberOfComments = 400,
            numberOfViews = 9000,
            creator = UserDetails(username = "fitness_guru"),
            visibility = VideoVisibility.PUBLIC
        ),
        Video(
            id = 9,
            title = "Music Cover #music",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test9",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2800,
            numberOfComments = 500,
            numberOfViews = 10000,
            creator = UserDetails(username = "music_artist"),
            visibility = VideoVisibility.PRIVATE
        ),
        Video(
            id = 10,
            title = "Art Process #art",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test10",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1500,
            numberOfComments = 280,
            numberOfViews = 6000,
            creator = UserDetails(username = "art_studio"),
            visibility = VideoVisibility.PUBLIC
        )
    )
}