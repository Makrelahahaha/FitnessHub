package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.DailyStats
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyStatsDao {

    @Query("SELECT * FROM daily_stats WHERE user_id = :userId AND date = :date")
    fun observeByDate(userId: String, date: String): Flow<DailyStats?>

    @Query(
        "SELECT * FROM daily_stats " +
            "WHERE user_id = :userId AND date BETWEEN :startDate AND :endDate " +
            "ORDER BY date"
    )
    fun observeForRange(
        userId: String,
        startDate: String,
        endDate: String
    ): Flow<List<DailyStats>>

    @Query("SELECT * FROM daily_stats WHERE user_id = :userId AND date = :date")
    suspend fun getByDate(userId: String, date: String): DailyStats?

    /**
     * The unique index on (user_id, date) makes REPLACE replace the existing row
     * for that day instead of creating a duplicate.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(stats: DailyStats)

    @Query("DELETE FROM daily_stats WHERE user_id = :userId")
    suspend fun deleteForUser(userId: String)
}
