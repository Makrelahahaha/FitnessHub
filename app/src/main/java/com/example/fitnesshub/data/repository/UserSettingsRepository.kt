package com.example.fitnesshub.data.repository

import com.example.fitnesshub.data.local.dao.UserSettingsDao
import com.example.fitnesshub.data.local.entity.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Single source of truth for the `user_settings` table.
 *
 * Reads never emit `null`: if the row is missing (for example on a database created
 * before seeding) the defaults of [UserSettings] are used, and the first write inserts it.
 */
class UserSettingsRepository(
    private val dao: UserSettingsDao,
    private val userId: String
) {

    private val writeMutex = Mutex()

    fun observeSettings(): Flow<UserSettings> =
        dao.observeSettings(userId).map { it ?: UserSettings(userId = userId) }

    suspend fun setLanguage(language: String) {
        update { it.copy(language = language) }
    }

    suspend fun setFontSize(fontSize: String) {
        update { it.copy(fontSize = fontSize) }
    }

    suspend fun setUnitsSystem(unitsSystem: String) {
        update { it.copy(unitsSystem = unitsSystem) }
    }

    suspend fun setTrainingReminders(enabled: Boolean) {
        update { it.copy(trainingReminders = enabled) }
    }

    suspend fun setWaterReminders(enabled: Boolean) {
        update { it.copy(waterReminders = enabled) }
    }

    private suspend fun update(transform: (UserSettings) -> UserSettings) {
        // Serialize read-modify-write so two quick changes can't drop each other.
        writeMutex.withLock {
            val current = dao.getSettings(userId) ?: UserSettings(userId = userId)
            val updated = transform(current)
            if (updated != current) {
                dao.upsert(updated)
            }
        }
    }
}
