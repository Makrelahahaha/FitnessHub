package com.example.fitnesshub.ui.home

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderAction
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun HomeScreen(
    onNavigateToPersonalData: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    PlaceholderPanel(
        title = "Hello!",
        actions = listOf(
            PlaceholderAction("Personal data", onNavigateToPersonalData),
            PlaceholderAction("Settings", onNavigateToSettings)
        )
    )
}
