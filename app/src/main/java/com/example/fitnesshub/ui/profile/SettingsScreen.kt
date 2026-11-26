package com.example.fitnesshub.ui.profile

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun SettingsScreen(onNavigateBack: () -> Unit = {}) {
    PlaceholderPanel(
        title = "Settings",
        onNavigateBack = onNavigateBack
    )
}