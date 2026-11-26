package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "workouts",
    foreignKeys = [
        ForeignKey(
            entity = Plan::class,
            parentColumns = ["id"],
            childColumns = ["plan_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("plan_id")]
)
data class Workout(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "plan_id")
    val planId: String? = null,
    val title: String,
    @ColumnInfo(name = "estimated_duration")
    val estimatedDuration: Int? = null,
    @ColumnInfo(name = "estimated_calories")
    val estimatedCalories: Int? = null,
    @ColumnInfo(name = "warmup_description")
    val warmupDescription: String? = null,
    @ColumnInfo(name = "cooldown_description")
    val cooldownDescription: String? = null
)
