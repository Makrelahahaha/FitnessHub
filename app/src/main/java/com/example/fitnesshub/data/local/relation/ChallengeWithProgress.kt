package com.example.fitnesshub.data.local.relation

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.example.fitnesshub.data.local.entity.Challenge

data class ChallengeWithProgress(
    @Embedded val challenge: Challenge,
    @ColumnInfo(name = "progress_current")
    val progressCurrent: Int,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
)
