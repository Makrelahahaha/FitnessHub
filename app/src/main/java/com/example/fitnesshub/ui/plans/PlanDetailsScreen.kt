package com.example.fitnesshub.ui.plans

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun PlanDetailsScreen(
    planId: String,
    onNavigateBack: () -> Unit = {}
) {
    PlaceholderPanel(
        title = "Plan Details",
        subtitle = planId,
        onNavigateBack = onNavigateBack
    )
}