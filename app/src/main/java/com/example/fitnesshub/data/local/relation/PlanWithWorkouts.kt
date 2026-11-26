package com.example.fitnesshub.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnesshub.data.local.entity.Plan
import com.example.fitnesshub.data.local.entity.Workout

data class PlanWithWorkouts(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "id",
        entityColumn = "plan_id"
    )
    val workouts: List<Workout>
)
