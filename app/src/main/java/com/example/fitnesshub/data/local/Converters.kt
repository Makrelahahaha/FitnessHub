package com.example.fitnesshub.data.local

import androidx.room.TypeConverter
import com.example.fitnesshub.data.model.BodyPart
import com.example.fitnesshub.data.model.Equipment
import com.example.fitnesshub.data.model.WorkoutType

class Converters {

    @TypeConverter
    fun bodyPartToString(value: BodyPart): String = value.name

    @TypeConverter
    fun stringToBodyPart(value: String): BodyPart = BodyPart.valueOf(value)

    @TypeConverter
    fun equipmentToString(value: Equipment): String = value.name

    @TypeConverter
    fun stringToEquipment(value: String): Equipment = Equipment.valueOf(value)

    @TypeConverter
    fun workoutTypeToString(value: WorkoutType): String = value.name

    @TypeConverter
    fun stringToWorkoutType(value: String): WorkoutType = WorkoutType.valueOf(value)
}
