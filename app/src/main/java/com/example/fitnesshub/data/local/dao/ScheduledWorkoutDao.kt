package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitnesshub.data.local.entity.ScheduledWorkout
import com.example.fitnesshub.data.local.relation.ScheduledWorkoutWithWorkout
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledWorkoutDao {

    @Query("SELECT * FROM scheduled_workouts WHERE user_id = :userId ORDER BY scheduled_at")
    fun observeForUser(userId: String): Flow<List<ScheduledWorkout>>

    @Transaction
    @Query("SELECT * FROM scheduled_workouts WHERE user_id = :userId ORDER BY scheduled_at")
    fun observeWithWorkoutsForUser(userId: String): Flow<List<ScheduledWorkoutWithWorkout>>

    @Query(
        "SELECT * FROM scheduled_workouts " +
            "WHERE user_id = :userId AND scheduled_at BETWEEN :start AND :end " +
            "ORDER BY scheduled_at"
    )
    fun observeForUserBetween(
        userId: String,
        start: Long,
        end: Long
    ): Flow<List<ScheduledWorkout>>

    @Query(
        "SELECT * FROM scheduled_workouts " +
            "WHERE user_id = :userId AND status = 'planned' AND scheduled_at >= :from " +
            "ORDER BY scheduled_at"
    )
    fun observeUpcoming(userId: String, from: Long): Flow<List<ScheduledWorkout>>

    @Query("SELECT * FROM scheduled_workouts WHERE id = :id")
    suspend fun getById(id: String): ScheduledWorkout?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(scheduledWorkout: ScheduledWorkout)

    @Query("UPDATE scheduled_workouts SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: String)

    @Delete
    suspend fun delete(scheduledWorkout: ScheduledWorkout)
}
