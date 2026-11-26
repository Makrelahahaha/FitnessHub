package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_challenges",
    primaryKeys = ["user_id", "challenge_id"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Challenge::class,
            parentColumns = ["id"],
            childColumns = ["challenge_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("challenge_id")]
)
data class UserChallenge(
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "challenge_id")
    val challengeId: String,
    @ColumnInfo(name = "progress_current")
    val progressCurrent: Int = 0,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false
)
