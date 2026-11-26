package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitnesshub.data.local.entity.Workout
import com.example.fitnesshub.data.local.entity.WorkoutExercise
import com.example.fitnesshub.data.local.relation.WorkoutWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts ORDER BY title")
    fun observeAll(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE plan_id = :planId ORDER BY title")
    fun observeByPlan(planId: String): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    suspend fun getById(workoutId: String): Workout?

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun observeWithExercises(workoutId: String): Flow<WorkoutWithExercises?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(workouts: List<Workout>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWorkoutExercise(entry: WorkoutExercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWorkoutExercises(entries: List<WorkoutExercise>)

    @Query("DELETE FROM workout_exercises WHERE workout_id = :workoutId")
    suspend fun clearExercisesForWorkout(workoutId: String)

    @Delete
    suspend fun delete(workout: Workout)
}
