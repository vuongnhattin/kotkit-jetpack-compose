package com.example.kotkit.ui.constant

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun topAppBarTitleStyle() = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)

@Composable
fun secondaryButtonColor() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.onSecondary
)