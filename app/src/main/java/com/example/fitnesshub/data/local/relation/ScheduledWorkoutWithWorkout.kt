package com.example.fitnesshub.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnesshub.data.local.entity.ScheduledWorkout
import com.example.fitnesshub.data.local.entity.Workout

data class ScheduledWorkoutWithWorkout(
    @Embedded val scheduledWorkout: ScheduledWorkout,
    @Relation(
        parentColumn = "workout_id",
        entityColumn = "id"
    )
    val workout: Workout
)
