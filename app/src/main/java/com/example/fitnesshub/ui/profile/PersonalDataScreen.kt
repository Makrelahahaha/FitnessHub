package com.example.fitnesshub.ui.profile

import androidx.compose.runtime.Composable
import com.example.fitnesshub.ui.components.PlaceholderPanel

@Composable
fun PersonalDataScreen(onNavigateBack: () -> Unit = {}) {
    PlaceholderPanel(
        title = "Personal data",
        onNavigateBack = onNavigateBack
    )
}