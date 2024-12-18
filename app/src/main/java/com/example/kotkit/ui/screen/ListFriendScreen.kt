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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.UserDetails
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.ui.component.CustomTextField
import com.example.kotkit.ui.component.UserList
import com.example.kotkit.ui.constant.TopAppBarTitleStyle
import com.example.kotkit.ui.icon.Search
import com.example.kotkit.ui.screen.utils.HandleApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFriendScreen(modifier: Modifier = Modifier, userId: Int, navController: NavController) {
    val userViewModel: UserViewModel = hiltViewModel()

    LaunchedEffect(userId) {
        userViewModel.getUserDetails(userId)
        userViewModel.getFriendsOfUser(userId)
    }

    val userState = userViewModel.userDetails
    val friendsState = userViewModel.listUserDetails

    HandleApiState(userState) { state1 ->
        HandleApiState(friendsState, onError = {e -> println(e.data.toString())}) { state2 ->
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                               state1.data?.username ?: "",
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

//    when {
//        userState is ApiState.Loading || friendsState is ApiState.Loading -> {
//            // Loading state
//        }
//
//        userState is ApiState.Error -> {
//            // Error state
//        }
//
//        friendsState is ApiState.Error -> {
//            // Error state
//        }
//
//        userState is ApiState.Success && friendsState is ApiState.Success -> {
//            Scaffold(
//                topBar = {
//                    CenterAlignedTopAppBar(
//                        title = {
//                            Text(
//                                userState.data!!.username,
//                                style = TopAppBarTitleStyle()
//                            )
//                        },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                            }
//                        }
//                    )
//                }
//            ) { innerPadding ->
//                ListFriendBody(
//                    modifier = Modifier.padding(innerPadding),
//                    friends = userViewModel.filteredListUser,
//                    navController = navController,
//                    userViewModel = userViewModel
//                )
//            }
//        }
//    }


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
            placeholder = "Tìm kiếm"
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
    ListFriendScreen(userId = 1, navController = rememberNavController())
}