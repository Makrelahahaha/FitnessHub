package com.example.fitnesshub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesshub.data.model.BodyPart
import com.example.fitnesshub.data.model.Equipment
import java.util.UUID

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    @ColumnInfo(name = "body_part")
    val bodyPart: BodyPart,
    val equipment: Equipment,
    @ColumnInfo(name = "default_sets")
    val defaultSets: Int = 3,
    @ColumnInfo(name = "default_reps")
    val defaultReps: Int = 10
)
