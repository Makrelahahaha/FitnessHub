package com.example.fitnesshub.ui.training

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun ExerciseLibraryScreen(onNavigateBack: () -> Unit = {}) {
    PlaceholderPanel(
        title = "Exercise Library",
        onNavigateBack = onNavigateBack
    )
}