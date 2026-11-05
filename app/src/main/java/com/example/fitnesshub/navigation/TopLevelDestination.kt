package com.example.fitnesshub.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.WavingHand
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TopLevelDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : TopLevelDestination("home", "Hello!", Icons.Outlined.WavingHand)
    data object Training : TopLevelDestination("training", "Training", Icons.Outlined.Accessibility)
    data object Calories : TopLevelDestination("calories", "Calories", Icons.Outlined.Stars)
    data object Plans : TopLevelDestination("plans", "Plans", Icons.AutoMirrored.Outlined.FormatListBulleted)
    data object Progress : TopLevelDestination("progress", "Progress", Icons.Outlined.StarBorder)
}

val topLevelDestinations: List<TopLevelDestination> = listOf(
    TopLevelDestination.Home,
    TopLevelDestination.Training,
    TopLevelDestination.Calories,
    TopLevelDestination.Plans,
    TopLevelDestination.Progress
)
