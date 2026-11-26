package com.example.fitnesshub.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnesshub.data.local.entity.User
import com.example.fitnesshub.data.local.entity.UserSettings

data class UserWithSettings(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val settings: UserSettings?
)
