package com.example.fitnesshub.ui.calories

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderAction
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun CaloriesScreen(onNavigateToWaterTracker: () -> Unit) {
    PlaceholderPanel(
        title = "Calories",
        actions = listOf(
            PlaceholderAction("Water Tracker", onNavigateToWaterTracker)
        )
    )
}
