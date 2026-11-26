package com.example.fitnesshub.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * The app uses a single light, lavender/purple brand scheme (see the Settings design).
 * Dynamic color is disabled so UI chrome and accents stay on-brand on every device.
 */
private val BrandColorScheme = lightColorScheme(
    primary = NavBarSelectedContent,
    onPrimary = Color.White,
    primaryContainer = NavBarIndicator,
    onPrimaryContainer = NavBarSelectedContent,
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    tertiary = Pink40,
    background = AppBackground,
    onBackground = AppOnBackground,
    surface = AppSurface,
    onSurface = AppOnBackground,
    surfaceVariant = NavBarIndicator,
    onSurfaceVariant = AppOnSurfaceVariant,
    outline = AppOutline,
    outlineVariant = AppOutlineVariant
)

@Composable
fun FitnessHubTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BrandColorScheme,
        typography = Typography,
        content = content
    )
}
