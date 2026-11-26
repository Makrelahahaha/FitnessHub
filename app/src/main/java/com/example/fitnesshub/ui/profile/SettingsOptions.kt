package com.example.fitnesshub.ui.profile

/** One selectable value of a settings dropdown. */
data class SettingOption(
    val value: String,
    val label: String
)

/**
 * Values stored in `user_settings` plus the labels shown in the UI.
 * `font_size` keeps `"medium"` as the stored default; the UI calls it "Default".
 */
object SettingsOptions {

    const val DefaultLanguage = "en"
    const val DefaultFontSize = "medium"
    const val DefaultUnitsSystem = "metric"

    val languages = listOf(
        SettingOption(DefaultLanguage, "English")
    )

    val fontSizes = listOf(
        SettingOption(DefaultFontSize, "Default"),
        SettingOption("small", "Small"),
        SettingOption("big", "Big")
    )

    val unitsSystems = listOf(
        SettingOption(DefaultUnitsSystem, "Metric"),
        SettingOption("imperial", "Imperial")
    )

    fun labelFor(options: List<SettingOption>, value: String): String =
        options.firstOrNull { it.value == value }?.label ?: options.first().label
}
