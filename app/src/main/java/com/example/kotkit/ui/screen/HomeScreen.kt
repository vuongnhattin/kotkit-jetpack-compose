package com.example.kotkit.ui.screen

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotkit.LocalNavController
import com.example.kotkit.data.viewmodel.CommentViewModel
import com.example.kotkit.ui.icon.Search

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val videoUri = Uri.parse("android.resource://${context.packageName}/raw/video_test")
    val navController = LocalNavController.current
    val commentViewModel: CommentViewModel = hiltViewModel()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(1f)
        ) {
            VideoPlayer(videoUri = videoUri)
            CommentScreen(
                viewModel = commentViewModel,
                videoId = 1,
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center
        ) {

            val elevation = 32.dp
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                val texts = listOf("Đề xuất", "Bạn bè")

                for (text in texts) {
                    Column(
                        modifier = Modifier
                            .shadow(elevation = elevation)
                    ) {
                        Text(
                            text = text,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.navigate("search/")
                }) {
                    Icon(
                        Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .shadow(elevation = elevation)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}