package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesshub.data.model.WorkoutType
import java.util.UUID

@Entity(tableName = "plans")
data class Plan(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val type: WorkoutType,
    @ColumnInfo(name = "duration_weeks")
    val durationWeeks: Int? = null,
    val frequency: String? = null
)
