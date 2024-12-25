package com.example.kotkit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.ui.common.FriendshipButton
import com.example.kotkit.ui.constant.TopAppBarTitleStyle
import com.example.kotkit.ui.icon.Lock
import com.example.kotkit.ui.icon.Public
import com.example.kotkit.ui.icon.Send
import com.example.kotkit.ui.icon.Share
import com.example.kotkit.ui.utils.DisplayApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    userId: Int,
) {
    val userViewModel: UserViewModel = hiltViewModel()
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        userViewModel.getUserDetails(userId)
    }

    val userDetailsState = userViewModel.userDetails

    DisplayApiResult(userDetailsState) { state ->
        val userDetails = state.data!!
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    userDetails.fullName, style = TopAppBarTitleStyle()
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }, actions = {
                IconButton(onClick = {

                }) {
                    Icon(Share, contentDescription = null, modifier = Modifier.size(24.dp))
                }
            })
        }) { innerPadding ->
            UserProfileBody(
                modifier = Modifier.padding(innerPadding),
                userDetails = userDetails,
                navController = navController
            )
        }
    }


}

@Composable
fun UserProfileBody(
    modifier: Modifier = Modifier, userDetails: UserDetails, navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(3.5f)
                .fillMaxSize()
        ) {
            ProfileDetailsSection(userDetails = userDetails, navController = navController)
        }

        Column(
            modifier = Modifier
                .weight(6.5f)
                .fillMaxSize()
        ) {
            VideoPreviewSection(userDetails = userDetails)
        }
    }
}

@Composable
fun ProfileDetailsSection(
    modifier: Modifier = Modifier, userDetails: UserDetails, navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = userDetails.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "@${userDetails.username}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
//                    .copy(fontWeight = FontWeight.ExtraBold)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
            navController.navigate("friends/${userDetails.id}")
        }) {
            Text(
                userDetails.numberOfFriends.toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
            Text(
                "Bạn bè",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            FriendshipButton(friendshipStatus = userDetails.friendStatus)
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Nhắn tin")
                }
            }
        }
    }
}

@Composable
fun VideoPreviewSection(modifier: Modifier = Modifier, userDetails: UserDetails) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(Public, Lock)

    Column(modifier = modifier) {
        TabRow(selectedTabIndex = selectedIndex, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedIndex]), color = Color.Black
            )
        }) {
            tabs.forEachIndexed { index, icon ->
                IconButton(
                    onClick = { selectedIndex = index },
//                modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = if (index == selectedIndex) Color.Black else Color.Gray
                    )
                }
            }
        }
    }

    val videoViewModel: VideoViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
//        videoViewModel.getPublicVideosOfUser(userDetails.id)
//        videoViewModel.getPrivateVideosOfUser(userDetails.id)
    }

    when (selectedIndex) {
        0 -> {
            VideoThumbnails(videosState = videoViewModel.publicVideos)
        }

        1 -> {
            VideoThumbnails(videosState = videoViewModel.privateVideos)
        }
    }
}

@Composable
fun VideoThumbnails(modifier: Modifier = Modifier, videosState: ApiState<List<Video>>) {
    DisplayApiResult(videosState, onError = {
        Text("Bạn phải kết bạn với người này để xem được video riêng tư của họ")
    }) { state ->
        val videos = state.data!!

        val space = 2.dp
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(space),
            horizontalArrangement = Arrangement.spacedBy(space),
            modifier = Modifier.fillMaxSize()
        ) {
            items(videos) { video ->
                AsyncImage(
                    model = video.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen(
        userId = 1
    )
}