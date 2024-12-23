// VideoMockData.kt
package com.example.kotkit.data.mock

import com.example.kotkit.data.model.Video
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.model.VideoMode

object VideoMockData {
    val videos = listOf(
        Video(
            id = 1,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "http://192.168.1.214:9001/api/v1/buckets/videos/objects/download?prefix=vid1.mp4&version_id=null",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1200,
            numberOfComments = 300,
            numberOfViews = 5000,
            creator = UserDetails(username = "nature_explorer"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 2,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "http://192.168.1.214:9001/api/v1/buckets/videos/objects/download?prefix=vid2.mov&version_id=null",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 800,
            numberOfComments = 150,
            numberOfViews = 3000,
            creator = UserDetails(username = "travel_life"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 3,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "http://192.168.1.214:9001/api/v1/buckets/videos/objects/download?prefix=vid3.mp4&version_id=null",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2500,
            numberOfComments = 450,
            numberOfViews = 8000,
            creator = UserDetails(username = "urban_shots"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 4,
            title = "Coffee Time #lifestyle",
            videoUrl = "http://192.168.1.214:9001/api/v1/buckets/videos/objects/download?prefix=vid4.mp4&version_id=null",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 950,
            numberOfComments = 200,
            numberOfViews = 4500,
            creator = UserDetails(username = "coffee_lover"),
            mode = VideoMode.PRIVATE
        ),
        Video(
            id = 5,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test11",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 3500,
            numberOfComments = 600,
            numberOfViews = 12000,
            creator = UserDetails(username = "dance_queen"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 6,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test11",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1800,
            numberOfComments = 350,
            numberOfViews = 7000,
            creator = UserDetails(username = "chef_life"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 7,
            title = "PRIVATE VIDEO TEST",
            videoUrl = "http://localhost:9001/browser/videos/vid10.mov",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 4200,
            numberOfComments = 750,
            numberOfViews = 15000,
            creator = UserDetails(username = "pet_lover"),
            mode = VideoMode.PRIVATE
        ),
        Video(
            id = 8,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test11",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2200,
            numberOfComments = 400,
            numberOfViews = 9000,
            creator = UserDetails(username = "fitness_guru"),
            mode = VideoMode.PUBLIC
        ),
        Video(
            id = 9,
            title = "Music Cover #music",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test10",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 2800,
            numberOfComments = 500,
            numberOfViews = 10000,
            creator = UserDetails(username = "music_artist"),
            mode = VideoMode.PRIVATE
        ),
        Video(
            id = 10,
            title = "PUBLIC VIDEO TEST",
            videoUrl = "android.resource://com.example.kotkit/raw/video_test11",
            thumbnail = "https://media.istockphoto.com/id/517188688/photo/mountain-landscape.jpg",
            numberOfLikes = 1500,
            numberOfComments = 280,
            numberOfViews = 6000,
            creator = UserDetails(username = "art_studio"),
            mode = VideoMode.PUBLIC
        )
    )
}