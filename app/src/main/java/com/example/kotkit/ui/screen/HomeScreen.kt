package com.example.kotkit.ui.screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.mock.VideoMockData
import com.example.kotkit.data.mock.VideoMockData.videos
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.model.VideoMode
import com.example.kotkit.data.viewmodel.CommentViewModel
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.presentation.components.VideoPlayerComponent
import com.example.kotkit.ui.icon.Search
import com.example.kotkit.ui.utils.DisplayApiResult

// Thanh dieu huong de xuat, ban be va icon tim kiem
@Composable
private fun TopBar(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        TabRow(
            modifier = Modifier
                .weight(1f),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) }
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(0.2f)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        ) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Moi che do (de xuat, ban be) se tao 1 videocontent
@Composable
private fun VideoContent(
    videosState: ApiState<List<Video>>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { videos.size })

    DisplayApiResult(
        state = videosState,

        onSuccess = { state ->
            state.data?.let { videos ->
                Log.d("VideoContent", "Videos ${videos.size}")
                VerticalPager(
                    state = pagerState,
                    modifier = modifier,
//                    key = { page -> videos[page].id }
                ) { page ->
                    VideoPlayerComponent(
                        video = videos[page],
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            } ?: run {
                Log.d("VideoContent", "No videos data")
            }
        }
    )
}

@OptIn(UnstableApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val navController = LocalNavController.current
    val commentViewModel: CommentViewModel = hiltViewModel()
    val videoViewModel: VideoViewModel = hiltViewModel()

    val publicVideosState = videoViewModel.publicVideos
    val privateVideosState = videoViewModel.privateVideos

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = remember { listOf("Đề xuất", "Bạn bè") }

    LaunchedEffect(Unit) {
        videoViewModel.getAllVideos()
        videoViewModel.getAllPublicVideos()
        videoViewModel.getAllPrivateVideos()
    }

    DisposableEffect(Unit) {
        onDispose {
        }
    }

    Box(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize(),
    ) {

        // Top Bar with Tabs and Search
        TopBar(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
            onSearchClick = { navController.navigate("search/") },
            modifier = Modifier
                .fillMaxWidth()
        )

        // Video Content
        Box(
            modifier = Modifier
                .padding(top = 100.dp) // Adjust based on TopBar height
                .fillMaxSize()
        ) {
            when (selectedTabIndex) {
                0 -> VideoContent(
                    videosState = publicVideosState,
                    modifier = Modifier.fillMaxSize(),
                )
                1 -> VideoContent(
                    videosState = privateVideosState,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}