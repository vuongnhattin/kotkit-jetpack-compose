package com.example.kotkit

import android.app.Application
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotkit.data.viewmodel.AuthViewModel
import com.example.kotkit.data.viewmodel.UserViewModel
import com.example.kotkit.ui.screen.BottomNavigationBar
import com.example.kotkit.ui.screen.CameraScreen
import com.example.kotkit.ui.screen.ChangePasswordScreen
import com.example.kotkit.ui.screen.ChatScreen
import com.example.kotkit.ui.screen.HomeScreen
import com.example.kotkit.ui.screen.NotificationScreen
import com.example.kotkit.ui.screen.MeProfileScreen
import com.example.kotkit.ui.screen.SearchResultScreen
import com.example.kotkit.ui.screen.SearchScreen
import com.example.kotkit.ui.screen.UserProfileScreen
import com.example.kotkit.ui.theme.KotkitTheme
import com.example.kotkit.ui.screen.ListFriendScreen
import com.example.kotkit.ui.screen.LoginScreen
import com.example.kotkit.ui.screen.MeProfileTopBar
import com.example.kotkit.ui.screen.RegisterScreen
import com.example.kotkit.ui.screen.SettingsScreen
import com.example.kotkit.ui.screen.UpdateInfoScreen
import com.example.kotkit.ui.screen.UploadVideoScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Application-level initialization can go here
    }
}

val LocalAuthViewModel = staticCompositionLocalOf<AuthViewModel> {
    error("AuthViewModel is not provided")
}

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController is not provided")
}

val LocalUserViewModel = staticCompositionLocalOf<UserViewModel> {
    error("UserViewModel is not provided")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KotkitTheme {
                val authViewModel: AuthViewModel = hiltViewModel()
                val userViewModel: UserViewModel = hiltViewModel()
                CompositionLocalProvider(
                    LocalAuthViewModel provides authViewModel,
                    LocalUserViewModel provides userViewModel
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screensWithBottomNav = listOf("home", "chat", "notification", "profile")

    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route

    val authViewModel = LocalAuthViewModel.current

    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            bottomBar = {
                if (currentRoute in screensWithBottomNav) {
                    BottomNavigationBar()
                }
            },
            topBar = {
                if (currentRoute == "profile") {
                    MeProfileTopBar()
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = if (authViewModel.isAuthenticated) "home" else "login",
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                modifier = if (currentRoute in screensWithBottomNav) {
                    Modifier.padding(innerPadding)
                } else {
                    Modifier
                }
            ) {
                composable("login") { LoginScreen() }
                composable("register") { RegisterScreen() }
                composable("home") { HomeScreen() }
                composable("camera") { CameraScreen() }
                composable(
                    "upload-video/{videoUri}",
                    arguments = listOf(navArgument("videoUri") { type = NavType.StringType })
                ) { backStackEntry ->
                    val videoUri =
                        backStackEntry.arguments?.getString("videoUri")?.let { Uri.parse(it) }
                    UploadVideoScreen(
                        videoUri = videoUri,
                    )
                }
                composable("chat") { ChatScreen() }
                composable("notification") { NotificationScreen() }
                composable("profile") { MeProfileScreen() }
                composable("setting") { SettingsScreen() }
                composable("update-info") { UpdateInfoScreen() }
                composable("change-password") { ChangePasswordScreen() }
                composable("search/{query}") { backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query") ?: ""
                    SearchScreen(query = query)
                }
                composable("search-result/{query}") { backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query") ?: ""
                    SearchResultScreen(query = query)
                }
                composable("user-profile/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                    UserProfileScreen(userId = userId)
                }
                composable("friends/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                    ListFriendScreen(userId = userId)
                }
            }
        }
    }
}