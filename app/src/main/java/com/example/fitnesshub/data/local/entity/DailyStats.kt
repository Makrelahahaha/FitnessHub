package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "daily_stats",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id", "date"], unique = true)]
)
data class DailyStats(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id")
    val userId: String,
    val date: String,
    @ColumnInfo(name = "steps_count")
    val stepsCount: Int = 0,
    @ColumnInfo(name = "active_calories")
    val activeCalories: Int = 0,
    @ColumnInfo(name = "water_consumed")
    val waterConsumed: Int = 0
)
