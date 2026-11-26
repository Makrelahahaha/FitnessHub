package com.example.fitnesshub.ui.training

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun TrainingCalendarScreen(onNavigateBack: () -> Unit = {}) {
    PlaceholderPanel(
        title = "Training Calendar",
        onNavigateBack = onNavigateBack
    )
}