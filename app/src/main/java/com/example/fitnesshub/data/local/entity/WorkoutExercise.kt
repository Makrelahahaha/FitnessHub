package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "workout_exercises",
    primaryKeys = ["workout_id", "exercise_id"],
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workout_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exercise_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("exercise_id")]
)
data class WorkoutExercise(
    @ColumnInfo(name = "workout_id")
    val workoutId: String,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: String,
    val sets: Int,
    val reps: Int,
    @ColumnInfo(name = "rest_time_seconds")
    val restTimeSeconds: Int = 60
)
