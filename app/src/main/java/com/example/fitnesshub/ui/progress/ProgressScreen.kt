package com.example.fitnesshub.ui.progress

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderAction
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun ProgressScreen(onNavigateToChallenges: () -> Unit) {
    PlaceholderPanel(
        title = "Progress",
        actions = listOf(
            PlaceholderAction("Challenges & Achievements", onNavigateToChallenges)
        )
    )
}
