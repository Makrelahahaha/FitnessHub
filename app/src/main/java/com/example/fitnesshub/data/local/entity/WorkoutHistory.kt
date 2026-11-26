package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "workout_history",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workout_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("user_id"), Index("workout_id")]
)
data class WorkoutHistory(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "workout_id")
    val workoutId: String? = null,
    @ColumnInfo(name = "completed_at")
    val completedAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "duration_actual")
    val durationActual: Int? = null,
    @ColumnInfo(name = "calories_burned")
    val caloriesBurned: Int? = null,
    @ColumnInfo(name = "xp_earned")
    val xpEarned: Int = 0
)
