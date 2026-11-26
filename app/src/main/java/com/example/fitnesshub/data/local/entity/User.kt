package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val weight: Double? = null,
    val height: Double? = null,
    @ColumnInfo(name = "daily_calorie_goal")
    val dailyCalorieGoal: Int = 2000,
    @ColumnInfo(name = "daily_water_goal")
    val dailyWaterGoal: Int = 2500,
    @ColumnInfo(name = "streak_days")
    val streakDays: Int = 0,
    @ColumnInfo(name = "total_xp")
    val totalXp: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
