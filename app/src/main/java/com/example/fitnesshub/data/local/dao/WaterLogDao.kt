package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.WaterLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterLogDao {

    @Query(
        "SELECT * FROM water_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end " +
            "ORDER BY logged_at DESC"
    )
    fun observeForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<List<WaterLog>>

    @Query(
        "SELECT COALESCE(SUM(amount), 0) FROM water_logs " +
            "WHERE user_id = :userId AND logged_at BETWEEN :start AND :end"
    )
    fun observeAmountForRange(
        userId: String,
        start: Long,
        end: Long
    ): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(log: WaterLog)

    @Delete
    suspend fun delete(log: WaterLog)

    @Query("DELETE FROM water_logs WHERE user_id = :userId")
    suspend fun deleteForUser(userId: String)
}
