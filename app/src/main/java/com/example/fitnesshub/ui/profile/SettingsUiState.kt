package com.example.fitnesshub.ui.profile

/** The stored `user_settings` row, adapted for the Settings screen. */
data class SettingsUiState(
    val isLoading: Boolean = true,
    val language: String = SettingsOptions.DefaultLanguage,
    val fontSize: String = SettingsOptions.DefaultFontSize,
    val unitsSystem: String = SettingsOptions.DefaultUnitsSystem,
    val trainingReminders: Boolean = true,
    val waterReminders: Boolean = true
)
