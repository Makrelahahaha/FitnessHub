package com.example.fitnesshub.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fitnesshub.data.local.DefaultUser
import com.example.fitnesshub.data.local.FitnessHubDatabase
import com.example.fitnesshub.data.local.entity.UserSettings
import com.example.fitnesshub.data.repository.UserSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Exposes the `user_settings` row as UI state.
 *
 * Changes are applied to [SettingsUiState] immediately and then written through the
 * repository. The Room `Flow` is still the source of truth, so the next emission
 * confirms (or corrects) the optimistic value.
 */
class SettingsViewModel(
    private val repository: UserSettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        repository.observeSettings()
            .onEach { settings -> _uiState.value = settings.toUiState() }
            .launchIn(viewModelScope)
    }

    fun onLanguageSelected(language: String) {
        if (_uiState.value.language == language) return
        _uiState.update { it.copy(language = language) }
        viewModelScope.launch { repository.setLanguage(language) }
    }

    fun onFontSizeSelected(fontSize: String) {
        if (_uiState.value.fontSize == fontSize) return
        _uiState.update { it.copy(fontSize = fontSize) }
        viewModelScope.launch { repository.setFontSize(fontSize) }
    }

    fun onUnitsSystemSelected(unitsSystem: String) {
        if (_uiState.value.unitsSystem == unitsSystem) return
        _uiState.update { it.copy(unitsSystem = unitsSystem) }
        viewModelScope.launch { repository.setUnitsSystem(unitsSystem) }
    }

    fun onTrainingRemindersChanged(enabled: Boolean) {
        if (_uiState.value.trainingReminders == enabled) return
        _uiState.update { it.copy(trainingReminders = enabled) }
        viewModelScope.launch { repository.setTrainingReminders(enabled) }
    }

    fun onWaterRemindersChanged(enabled: Boolean) {
        if (_uiState.value.waterReminders == enabled) return
        _uiState.update { it.copy(waterReminders = enabled) }
        viewModelScope.launch { repository.setWaterReminders(enabled) }
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val dao = FitnessHubDatabase.getInstance(context).userSettingsDao()
                SettingsViewModel(
                    repository = UserSettingsRepository(dao, DefaultUser.ID)
                )
            }
        }
    }
}

private fun UserSettings.toUiState(): SettingsUiState = SettingsUiState(
    isLoading = false,
    language = language,
    fontSize = fontSize,
    unitsSystem = unitsSystem,
    trainingReminders = trainingReminders,
    waterReminders = waterReminders
)
