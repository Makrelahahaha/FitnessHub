package com.example.fitnesshub.ui.plans

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderAction
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun PlansScreen(onNavigateToPlanDetails: () -> Unit) {
    PlaceholderPanel(
        title = "Plans",
        actions = listOf(
            PlaceholderAction("View Plan", onNavigateToPlanDetails)
        )
    )
}
