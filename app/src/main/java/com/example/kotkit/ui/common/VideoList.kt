package com.example.kotkit.ui.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.kotkit.LocalVideoViewModel
import com.example.kotkit.R
import com.example.kotkit.data.api.BASE_URL_MINIO
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.ui.icon.Comment
import com.example.kotkit.ui.icon.Heart
import com.example.kotkit.ui.theme.WhiteColor
import com.example.kotkit.ui.utils.FormatUtils.formatImageUrl
import com.example.kotkit.ui.utils.FormatUtils.formatNumber

@Composable
fun VideoDetailsItem(
    modifier: Modifier = Modifier,
    video: Video,
    navController: NavController,
) {

    val videoViewModel: VideoViewModel = LocalVideoViewModel.current

    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = {
            videoViewModel.selectVideoToPlay(video)
            navController.navigate("search-result/video")
        }
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            // Thumbnail
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(formatImageUrl(video.thumbnail))
                    .transformations(RoundedCornersTransformation(topLeft = 8f, topRight = 8f))
                    .build(),
                contentDescription = "Video thumbnail",
                modifier = Modifier
                    .weight(0.8f),
                contentScale = ContentScale.Crop
            )

            // Video info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                // Title
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Creator info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Creator avatar
                    Row(
                        modifier = Modifier.weight(0.75f)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(video.creator.avatar)
                                .transformations(RoundedCornersTransformation(radius = 20f))
                                .build(),
                            contentDescription = "Creator avatar",
                            modifier = Modifier
                                .size(20.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = video.creator.fullName,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            maxLines = 1,
                        )
                    }

                    // React
                    Row(
                        modifier = Modifier.weight(0.25f)
                    ) {
                        Icon(
                            imageVector = Heart,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = formatNumber(video.numberOfLikes),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun VideoDetailsList(
    modifier: Modifier = Modifier,
    videos: List<Video>,
    navController: NavController,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(videos.size) { index ->
            VideoDetailsItem(
                video = videos[index],
                navController = navController,
            )
        }
    }
}

