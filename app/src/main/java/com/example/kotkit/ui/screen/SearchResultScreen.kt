package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotkit.LocalNavController
import com.example.kotkit.ui.common.CustomTextField
import com.example.kotkit.ui.icon.DotsHorizontal
import com.example.kotkit.ui.icon.Search
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.ui.common.UserList
import com.example.kotkit.ui.utils.DisplayApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    query: String = "",
) {
    var text by remember { mutableStateOf(TextFieldValue(query)) }
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomTextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier
                                .weight(0.7f)
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) {
                                        navController.navigate("search/${text.text}")
                                    }
                                },
//                                .background(MaterialTheme.colorScheme.secondary),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            leadingIcon = {
                                Icon(Search, contentDescription = "Back")
                            },
                        )
                        IconButton(onClick = {

                        }) {
                            Icon(
                                DotsHorizontal,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("search/")
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        SearchScreenTabRow(
            modifier = Modifier.padding(innerPadding),
            query = text,
            navController = navController
        )
    }
}

@Composable
fun SearchScreenTabRow(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    navController: NavController
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Video", "Người dùng")

    Column(modifier = modifier) {
        TabRow(selectedTabIndex = selectedIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = Color.Black
                )
            }) {
            tabs.forEachIndexed { index, title ->
                TextButton(
                    onClick = { selectedIndex = index },
//                modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(title, color = if (index == selectedIndex) Color.Black else Color.Gray)
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            when (selectedIndex) {
                0 -> {
                    VideoSearchResult()
                }

                1 -> {
                    UserSearchResult(query = query, navController = navController)
                }
            }
        }
    }
}

@Composable
fun VideoSearchResult(modifier: Modifier = Modifier) {
    Text("Video result")
}


@Composable
fun UserSearchResult(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    navController: NavController
) {
    val userViewModel = hiltViewModel<UserViewModel>()
    val userSearchResult = userViewModel.listUserDetails

    LaunchedEffect(query.text) {
        userViewModel.searchUsers(query.text)
    }

    DisplayApiResult(userSearchResult) { res ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            UserList(
                users =  res.data!!,
                navController = navController
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SearchResultScreenPreview() {
    SearchResultScreen()
}
