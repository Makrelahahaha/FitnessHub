package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "reward_xp")
    val rewardXp: Int,
    @ColumnInfo(name = "target_value")
    val targetValue: Int
)
