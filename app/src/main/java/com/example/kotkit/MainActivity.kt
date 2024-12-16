package com.example.kotkit

import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.kotkit.data.viewmodel.AuthViewModel
import com.example.kotkit.ui.screen.BottomNavigationBar
import com.example.kotkit.ui.screen.ChatScreen
import com.example.kotkit.ui.screen.HomeScreen
import com.example.kotkit.ui.screen.NotificationScreen
import com.example.kotkit.ui.screen.ProfileScreen
import com.example.kotkit.ui.screen.SearchResultScreen
import com.example.kotkit.ui.screen.SearchScreen
import com.example.kotkit.ui.screen.UserProfileScreen
import com.example.kotkit.ui.theme.KotkitTheme
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.data.viewmodel.VideoViewModel
import com.example.kotkit.ui.screen.ListFriendScreen
import com.example.kotkit.ui.screen.LoginScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.log

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Application-level initialization can go here
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KotkitTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val screensWithBottomNav = listOf("home", "chat", "notification", "profile")

    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route

    val authViewModel: AuthViewModel = hiltViewModel()
    val authenticated = authViewModel.authenticated

    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = if (currentRoute in screensWithBottomNav) {
                Modifier.padding(innerPadding)
            } else {
                Modifier
            }
        ) {
            composable("login") { LoginScreen(navController = navController, authViewModel = authViewModel) }
            composable("home") { HomeScreen(navController = navController) }
            composable("chat") { ChatScreen(navController = navController) }
            composable("notification") { NotificationScreen(navController = navController) }
            composable("profile") { ProfileScreen(navController = navController, authViewModel = authViewModel) }
            composable("search/{query}") { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchScreen(navController = navController, query = query)
            }
            composable("search-result/{query}") { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchResultScreen(navController = navController, query = query)
            }
            composable("user-profile/{userId}") { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                UserProfileScreen(navController = navController, userId = userId)
            }
            composable("friends/{userId}") { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                ListFriendScreen(navController = navController, userId = userId)
            }
        }

        if (!authenticated && currentRoute != "login") {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Phiên đăng nhập hết hạn") },
                text = { Text("Phiên đăng nhập của bạn đã hết hạn, vui lòng đăng nhập lại") },
                confirmButton = {
                    Button(onClick = {
                        navController.navigate("login") // Navigate to login page
                    }) {
                        Text("Đăng nhập")
                    }
                }
            )
        }
    }
}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationPreview() {
    MyApp()
}