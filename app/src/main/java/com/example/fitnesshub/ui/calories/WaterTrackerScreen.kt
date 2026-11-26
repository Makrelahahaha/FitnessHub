package com.example.fitnesshub.ui.calories

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun WaterTrackerScreen(onNavigateBack: () -> Unit = {}) {
    PlaceholderPanel(
        title = "Water Tracker",
        onNavigateBack = onNavigateBack
    )
}