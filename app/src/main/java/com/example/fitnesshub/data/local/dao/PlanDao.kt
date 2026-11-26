package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitnesshub.data.local.entity.Plan
import com.example.fitnesshub.data.local.relation.PlanWithWorkouts
import com.example.fitnesshub.data.model.WorkoutType
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {

    @Query("SELECT * FROM plans ORDER BY title")
    fun observeAll(): Flow<List<Plan>>

    @Query("SELECT * FROM plans WHERE type = :type ORDER BY title")
    fun observeByType(type: WorkoutType): Flow<List<Plan>>

    @Query("SELECT * FROM plans WHERE id = :planId")
    suspend fun getById(planId: String): Plan?

    @Transaction
    @Query("SELECT * FROM plans WHERE id = :planId")
    fun observeWithWorkouts(planId: String): Flow<PlanWithWorkouts?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(plan: Plan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(plans: List<Plan>)

    @Delete
    suspend fun delete(plan: Plan)
}
