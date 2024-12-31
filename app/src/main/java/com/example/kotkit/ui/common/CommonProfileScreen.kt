package com.example.kotkit.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotkit.LocalNavController
import com.example.kotkit.LocalUserViewModel
import com.example.kotkit.LocalVideoViewModel
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.model.Video
import com.example.kotkit.data.viewmodel.UploadFileViewModel
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.ui.constant.secondaryButtonColor
import com.example.kotkit.ui.constant.topAppBarTitleStyle
import com.example.kotkit.ui.icon.Bookmark
import com.example.kotkit.ui.icon.Lock
import com.example.kotkit.ui.icon.Photo_camera
import com.example.kotkit.ui.icon.Public
import com.example.kotkit.ui.icon.Send
import com.example.kotkit.ui.icon.Share
import com.example.kotkit.ui.icon.Users
import com.example.kotkit.ui.utils.DisplayApiResult
import com.example.kotkit.ui.utils.ErrorSnackBar
import com.example.kotkit.ui.utils.FormatUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonProfileScreen(modifier: Modifier = Modifier, userId: Int = 0, isMe: Boolean = false) {
    val userViewModel = LocalUserViewModel.current
    val navController = LocalNavController.current

    val userState = userViewModel.userDetails

    LaunchedEffect(Unit) {
        if (isMe) {
            userViewModel.getMe()
        } else {
            userViewModel.getUserDetails(userId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DisplayApiResult(
            state = userState
        ) { state ->
            val userDetails = state.data ?: UserDetails()

            Scaffold(

                topBar = {
                    if (!isMe)
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    userDetails.fullName, style = topAppBarTitleStyle()
                                )

                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                }
                            },
                            actions = {
                                IconButton(onClick = {

                                }) {
                                    Icon(
                                        Share,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                        )
                }) { innerPadding ->
                ProfileContent(
                    modifier = if (!isMe) Modifier.padding(innerPadding) else Modifier,
                    userDetails = userDetails,
                    navController = navController,
                    userViewModel = userViewModel,
                    isMe = isMe
                )
            }
        }
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    userDetails: UserDetails,
    navController: NavController,
    userViewModel: UserViewModel,
    isMe: Boolean
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(if (!isMe) 3.5f else 4f)
                .fillMaxSize()
        ) {
            UserInfoSection(
                userDetails = userDetails,
                navController = navController,
                userViewModel = userViewModel,
                isMe = isMe
            )
        }

        Column(
            modifier = Modifier
                .weight(6.5f)
                .fillMaxSize()
        ) {
            VideoPreviewSection(userDetails = userDetails, isMe = isMe)
        }
    }
}

@Composable
fun UserInfoSection(
    modifier: Modifier = Modifier,
    userDetails: UserDetails,
    navController: NavController,
    userViewModel: UserViewModel,
    isMe: Boolean
) {
    var showUpdateAvatarDialog by remember { mutableStateOf(false) }
    var showNullAvatarDialog by remember { mutableStateOf(false) }
    val uploadFileViewModel: UploadFileViewModel = hiltViewModel()
    val context = LocalContext.current
    val updateAvatarResponse = uploadFileViewModel.updateAvatarResponse

    var messageError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = userDetails.avatar,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .border(1.dp, Color.Gray, CircleShape)
                        .then(if (isMe) {
                            Modifier.clickable {
                                showUpdateAvatarDialog = true // Hiển thị dialog khi click
                            }
                        } else Modifier)
                )

                if (isMe) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Photo_camera,
                            contentDescription = "Change avatar",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            if (showUpdateAvatarDialog) {
                UpdateAvatarDialog(
                    onDismiss = {
                        showUpdateAvatarDialog = false
                    },
                    onSaveClick = { uri ->
                        if(uri == null) {
                            showNullAvatarDialog = true
                        }
                        uri?.let {
                            uploadFileViewModel.updateAvatar(context, it)
                        }
                        showUpdateAvatarDialog = false
                    }
                )
            }

            if (showNullAvatarDialog) {
                AlertDialog(
                    onDismissRequest = { /* Dismiss the dialog */ },
                    title = { Text("Thay đổi ảnh đại diện thất bại") },
                    text = { Text("Không có ảnh nào được chọn!") },
                    confirmButton = {
                        TextButton(onClick = {
                            showNullAvatarDialog = false
                            uploadFileViewModel.setUpdateAvatarResponseToEmpty()
                        }) {
                            Text("OK")
                        }
                    }
                )
            }

            DisplayApiResult(updateAvatarResponse,
                onError = { error ->
                    when (error.code) {
                        "VALIDATION_ERROR" -> error.data?.let { data ->
                            val dataMap = data as Map<String, String>
                            messageError = when (dataMap["avatar"]) {
                                "AVATAR_REQUIRED" -> "Không có ảnh nào được chọn!"
                                "PAYLOAD_TOO_LARGE" -> "Dung lượng video quá lớn (không được quá 100MB)!"
                                else -> ""
                            }
                        }
                        "AVATAR_EMPTY" -> messageError = "Không có ảnh nào được chọn!"
                        "SAVING_ERROR" -> messageError = "Lỗi lưu trữ của Server!"
                        else -> messageError = "Lỗi không xác định!"
                    }

                    AlertDialog(
                        onDismissRequest = { /* Dismiss the dialog */ },
                        title = { Text("Thay đổi ảnh đại diện thất bại") },
                        text = { Text(messageError) },
                        confirmButton = {
                            TextButton(onClick = {
                                messageError = ""
                                uploadFileViewModel.setUpdateAvatarResponseToEmpty()
                            }) {
                                Text("OK")
                            }
                        }
                    )
                }
            ) {
                AlertDialog(
                    onDismissRequest = { /* Dismiss the dialog */ },
                    title = { Text("Thành công") },
                    text = { Text("Ảnh đại diện của bạn đã được thay đổi") },
                    confirmButton = {
                        TextButton(onClick = {
                            uploadFileViewModel.setUpdateAvatarResponseToEmpty()
                        }) {
                            Text("OK")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                userDetails.email,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
            navController.navigate("friends/${userDetails.userId}")
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
            if (!isMe) {

                FriendshipButton(
                    friendshipStatus = userDetails.friendStatus,
                    userId = userDetails.userId
                )
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = {},
                    shape = MaterialTheme.shapes.medium,
                    colors = secondaryButtonColor()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Send, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Nhắn tin")
                    }
                }
            } else {
                CustomButton(
                    onClick = {
                        navController.navigate("update-info")
                    },
                    colors = secondaryButtonColor(),
                ) {
                    Text("Sửa hồ sơ")
                }
            }
        }
    }
}

@Composable
fun VideoPreviewSection(modifier: Modifier = Modifier, userDetails: UserDetails, isMe: Boolean) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabs = mutableListOf(Public, Users)
    if (isMe) {
        tabs.add(Lock)
        tabs.add(Bookmark)
    }

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

    val videoViewModel = LocalVideoViewModel.current

    LaunchedEffect(Unit) {
        videoViewModel.getPublicVideosOfUser(userDetails.userId)
        videoViewModel.getFriendVideosOfUser(userDetails.userId)
        if (isMe) {
            videoViewModel.getPrivateVideosOfMe()
            videoViewModel.getSavedVideosOfMe()
        }
    }

    when (selectedIndex) {
        0 -> {
            VideoThumbnails(videosState = videoViewModel.publicVideosOfUser)
        }

        1 -> {
            VideoThumbnails(videosState = videoViewModel.friendVideosOfUser)
        }

        2 -> {
            VideoThumbnails(videosState = videoViewModel.privateVideosOfMe)
        }

        3 -> {
            VideoThumbnails(videosState = videoViewModel.savedVideosOfMe)
        }
    }
}

@Composable
fun VideoThumbnails(modifier: Modifier = Modifier, videosState: ApiState<List<Video>>) {
    DisplayApiResult(videosState, onError = { error ->
        when (error.code) {
            "NOT_FRIEND" -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Lock, contentDescription = null, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Bạn phải kết bạn với người này để xem được những video này", textAlign = TextAlign.Center, color = Color.Gray)
                }
            }
            else -> ErrorSnackBar("Có lỗi xảy ra")
        }
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
                    model = FormatUtils.formatImageUrl(video.thumbnail),
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