package com.example.kotkit.ui.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.LocalNavController
import com.example.kotkit.ui.constant.topAppBarTitleStyle
import com.example.kotkit.ui.icon.Keyboard_arrow_right
import com.example.kotkit.ui.icon.Lock
import com.example.kotkit.ui.icon.Logout
import com.example.kotkit.ui.icon.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        text = "Cài đặt",
                        style = topAppBarTitleStyle()
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SettingsContent()
        }
    }
}

@Composable
fun SettingsContent() {
    val authViewModel = LocalAuthViewModel.current
    val navController = LocalNavController.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Section(
            title = "Tài khoản",
            items = listOf(
                SettingItem(User, "Sửa thông tin", { navController.navigate("update-info") }),
                SettingItem(Lock, "Đổi mật khẩu"),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Section(
            title = "Đăng nhập",
            items = listOf(
                SettingItem(Logout, "Đăng xuất", { authViewModel.logout() }),
            )
        )
    }
}

@Composable
fun Section(title: String, items: List<SettingItem>) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Card(
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onSecondary,
            containerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Column {
            items.forEachIndexed { index, item ->
                SettingRow(item)
                if (index < items.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun SettingRow(item: SettingItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = item.icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.text, style = MaterialTheme.typography.titleSmall)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(Keyboard_arrow_right, contentDescription = null)
        }
    }
}

data class SettingItem(val icon: ImageVector, val text: String, val onClick: () -> Unit = {})