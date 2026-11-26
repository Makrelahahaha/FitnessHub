package com.example.fitnesshub.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnesshub.data.local.dao.CalorieLogDao
import com.example.fitnesshub.data.local.dao.ChallengeDao
import com.example.fitnesshub.data.local.dao.DailyStatsDao
import com.example.fitnesshub.data.local.dao.ExerciseDao
import com.example.fitnesshub.data.local.dao.PlanDao
import com.example.fitnesshub.data.local.dao.ScheduledWorkoutDao
import com.example.fitnesshub.data.local.dao.UserDao
import com.example.fitnesshub.data.local.dao.UserSettingsDao
import com.example.fitnesshub.data.local.dao.WaterLogDao
import com.example.fitnesshub.data.local.dao.WorkoutDao
import com.example.fitnesshub.data.local.dao.WorkoutHistoryDao
import com.example.fitnesshub.data.local.entity.CalorieLog
import com.example.fitnesshub.data.local.entity.Challenge
import com.example.fitnesshub.data.local.entity.DailyStats
import com.example.fitnesshub.data.local.entity.Exercise
import com.example.fitnesshub.data.local.entity.Plan
import com.example.fitnesshub.data.local.entity.ScheduledWorkout
import com.example.fitnesshub.data.local.entity.User
import com.example.fitnesshub.data.local.entity.UserChallenge
import com.example.fitnesshub.data.local.entity.UserSettings
import com.example.fitnesshub.data.local.entity.WaterLog
import com.example.fitnesshub.data.local.entity.Workout
import com.example.fitnesshub.data.local.entity.WorkoutExercise
import com.example.fitnesshub.data.local.entity.WorkoutHistory

@Database(
    entities = [
        User::class,
        UserSettings::class,
        Plan::class,
        Exercise::class,
        Workout::class,
        WorkoutExercise::class,
        ScheduledWorkout::class,
        WorkoutHistory::class,
        CalorieLog::class,
        WaterLog::class,
        DailyStats::class,
        Challenge::class,
        UserChallenge::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class FitnessHubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun planDao(): PlanDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun scheduledWorkoutDao(): ScheduledWorkoutDao
    abstract fun workoutHistoryDao(): WorkoutHistoryDao
    abstract fun calorieLogDao(): CalorieLogDao
    abstract fun waterLogDao(): WaterLogDao
    abstract fun dailyStatsDao(): DailyStatsDao
    abstract fun challengeDao(): ChallengeDao

    companion object {
        const val DATABASE_NAME = "fitness_hub.db"

        @Volatile
        private var INSTANCE: FitnessHubDatabase? = null

        fun getInstance(context: Context): FitnessHubDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FitnessHubDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(DefaultDataSeeder)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
