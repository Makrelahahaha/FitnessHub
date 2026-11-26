package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.CalorieLog
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieLogDao {

    @Query(
        "SELECT * FROM calorie_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end " +
            "ORDER BY logged_at DESC"
    )
    fun observeForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<List<CalorieLog>>

    @Query(
        "SELECT COALESCE(SUM(calories), 0) FROM calorie_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end"
    )
    fun observeCaloriesForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<Int>

    @Query(
        "SELECT COALESCE(SUM(protein), 0.0) FROM calorie_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end"
    )
    fun observeProteinForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<Double>

    @Query(
        "SELECT COALESCE(SUM(carbs), 0.0) FROM calorie_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end"
    )
    fun observeCarbsForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<Double>

    @Query(
        "SELECT COALESCE(SUM(fat), 0.0) FROM calorie_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end"
    )
    fun observeFatForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(log: CalorieLog)

    @Delete
    suspend fun delete(log: CalorieLog)

    @Query("DELETE FROM calorie_logs WHERE user_id = :userId")
    suspend fun deleteForUser(userId: String)
}
