package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.common.UserList
import com.example.kotkit.ui.constant.TopAppBarTitleStyle
import com.example.kotkit.ui.icon.Search
import com.example.kotkit.ui.utils.DisplayApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFriendScreen(modifier: Modifier = Modifier, userId: Int) {
    val userViewModel: UserViewModel = hiltViewModel()
    val navController = LocalNavController.current

    LaunchedEffect(userId) {
        userViewModel.getUserDetails(userId)
        userViewModel.getFriendsOfUser(userId)
    }

    val userState = userViewModel.userDetails
    val friendsState = userViewModel.listUserDetails

    DisplayApiResult(userState) { user ->
        DisplayApiResult(friendsState) { _ ->
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                user.data?.username ?: "",
                                style = TopAppBarTitleStyle()
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                ListFriendBody(
                    modifier = Modifier.padding(innerPadding),
                    friends = userViewModel.filteredListUser,
                    navController = navController,
                    userViewModel = userViewModel
                )
            }
        }
    }
}

@Composable
fun ListFriendBody(
    modifier: Modifier = Modifier,
    friends: List<UserDetails>,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var text by remember {
        mutableStateOf(
            TextFieldValue(
                ""
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        CustomTextField(
            value = text,
            onValueChange = {
                text = it
                userViewModel.filterFriendsOfUser(it.text)
            },
            leadingIcon = {
                Icon(Search, contentDescription = "Back")
            },
            placeholder = { Text("Tìm kiếm") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        UserList(
            users = friends,
            navController = navController
        )
    }
}

@Preview
@Composable
private fun ListFriendPreview() {
    ListFriendScreen(userId = 1)
}