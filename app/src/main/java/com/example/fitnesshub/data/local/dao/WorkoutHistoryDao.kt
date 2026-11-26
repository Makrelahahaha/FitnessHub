package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.WorkoutHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutHistoryDao {

    @Query("SELECT * FROM workout_history WHERE user_id = :userId ORDER BY completed_at DESC")
    fun observeForUser(userId: String): Flow<List<WorkoutHistory>>

    @Query(
        "SELECT * FROM workout_history " +
            "WHERE user_id = :userId AND completed_at BETWEEN :start AND :end " +
            "ORDER BY completed_at DESC"
    )
    fun observeForUserBetween(
        userId: String,
        start: Long,
        end: Long
    ): Flow<List<WorkoutHistory>>

    @Query("SELECT * FROM workout_history WHERE id = :id")
    suspend fun getById(id: String): WorkoutHistory?

    @Query("SELECT COUNT(*) FROM workout_history WHERE user_id = :userId")
    fun observeCountForUser(userId: String): Flow<Int>

    @Query("SELECT COALESCE(SUM(xp_earned), 0) FROM workout_history WHERE user_id = :userId")
    fun observeTotalXpForUser(userId: String): Flow<Int>

    @Query("SELECT COALESCE(SUM(calories_burned), 0) FROM workout_history WHERE user_id = :userId")
    fun observeTotalCaloriesForUser(userId: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(history: WorkoutHistory)

    @Delete
    suspend fun delete(history: WorkoutHistory)
}
