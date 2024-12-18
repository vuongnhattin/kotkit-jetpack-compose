package com.example.kotkit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotkit.ui.icon.Search
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.viewmodel.AuthViewModel
import com.example.kotkit.ui.component.CustomTextField
import com.example.kotkit.data.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, query: String = "") {
    val navController = LocalNavController.current

    var text by remember {
        mutableStateOf(
            TextFieldValue(
                query,
                selection = TextRange(query.length)
            )
        )
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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
                            onValueChange = {
                                text = it
                            },
                            modifier = Modifier
                                .weight(0.5f)
                                .focusRequester(focusRequester),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            leadingIcon = {
                                Icon(Search, contentDescription = "Back")
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search // Set the IME action to "Done"
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    // Perform your action here
                                    navController.navigate("search-result/${text.text}")
                                    println("Done button clicked! Input text: $text")
                                    // You can also hide the keyboard programmatically here if needed
                                }
                            )
                        )
                        TextButton(
                            onClick = {
                                navController.navigate("search-result/${text.text}")
                            },
                            enabled = text.text.isNotEmpty()
                        ) {
                            Text(
                                text = "Tìm kiếm"
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        SearchScreenBody(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SearchScreenBody(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = modifier,
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}