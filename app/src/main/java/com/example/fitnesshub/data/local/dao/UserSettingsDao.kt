package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.UserSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSettingsDao {

    @Query("SELECT * FROM user_settings WHERE user_id = :userId")
    fun observeSettings(userId: String): Flow<UserSettings?>

    @Query("SELECT * FROM user_settings WHERE user_id = :userId")
    suspend fun getSettings(userId: String): UserSettings?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(settings: UserSettings)

    @Query("DELETE FROM user_settings WHERE user_id = :userId")
    suspend fun deleteForUser(userId: String)
}
