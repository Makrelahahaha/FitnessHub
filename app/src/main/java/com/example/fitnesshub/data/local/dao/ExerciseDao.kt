package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.Exercise
import com.example.fitnesshub.data.model.BodyPart
import com.example.fitnesshub.data.model.Equipment
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises ORDER BY name")
    fun observeAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :query || '%' ORDER BY name")
    fun search(query: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE body_part = :bodyPart ORDER BY name")
    fun observeByBodyPart(bodyPart: BodyPart): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE equipment = :equipment ORDER BY name")
    fun observeByEquipment(equipment: Equipment): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    suspend fun getById(exerciseId: String): Exercise?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(exercises: List<Exercise>)

    @Delete
    suspend fun delete(exercise: Exercise)
}
