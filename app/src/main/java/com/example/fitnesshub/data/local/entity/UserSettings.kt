package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_settings",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserSettings(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,
    val language: String = "en",
    @ColumnInfo(name = "font_size")
    val fontSize: String = "medium",
    @ColumnInfo(name = "units_system")
    val unitsSystem: String = "metric",
    @ColumnInfo(name = "training_reminders")
    val trainingReminders: Boolean = true,
    @ColumnInfo(name = "water_reminders")
    val waterReminders: Boolean = true
)
