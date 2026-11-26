package com.example.fitnesshub.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnesshub.data.local.entity.Exercise
import com.example.fitnesshub.data.local.entity.Workout
import com.example.fitnesshub.data.local.entity.WorkoutExercise

data class WorkoutExerciseWithExercise(
    @Embedded val entry: WorkoutExercise,
    @Relation(
        parentColumn = "exercise_id",
        entityColumn = "id"
    )
    val exercise: Exercise
)

data class WorkoutWithExercises(
    @Embedded val workout: Workout,
    @Relation(
        entity = WorkoutExercise::class,
        parentColumn = "id",
        entityColumn = "workout_id"
    )
    val entries: List<WorkoutExerciseWithExercise>
)
