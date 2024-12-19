package com.example.kotkit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.R
import com.example.kotkit.ui.icon.Chat_bubble
import com.example.kotkit.ui.icon.Home
import com.example.kotkit.ui.icon.Notifications
import com.example.kotkit.ui.icon.User

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun BottomNavigationBar(
) {
    val items = listOf(
        BottomNavItem("home", "Trang chủ", Home),
        BottomNavItem("chat", "Tin nhắn", Chat_bubble),
        BottomNavItem("notification", "Th Báo", Notifications),
        BottomNavItem("profile", "Tài khoản", User),
    )

    val navController = LocalNavController.current

    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.Black,
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        items.forEachIndexed() { index, item ->
            if (index == 2) {

                IconButton(
                    onClick = {
                        navController.navigate("upload-video")
                    },
                    modifier = Modifier.size(64.dp)
                ) {
                    Image(
                        painterResource(R.drawable.add_video), contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) },
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = null) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
