package com.example.fitnesshub.ui.training

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun WorkoutDetailsScreen(workoutId: String) {
    PlaceholderPanel(
        title = "Workout Details",
        subtitle = workoutId
    )
}
