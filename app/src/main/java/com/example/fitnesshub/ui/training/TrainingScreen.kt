package com.example.fitnesshub.ui.training

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderAction
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun TrainingScreen(
    onNavigateToCalendar: () -> Unit,
    onNavigateToLibrary: () -> Unit,
    onNavigateToWorkoutDetails: () -> Unit
) {
    PlaceholderPanel(
        title = "Training",
        actions = listOf(
            PlaceholderAction("Training Calendar", onNavigateToCalendar),
            PlaceholderAction("Exercise Library", onNavigateToLibrary),
            PlaceholderAction("Workout Details", onNavigateToWorkoutDetails)
        )
    )
}
