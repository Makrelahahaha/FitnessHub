package com.example.fitnesshub.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Inserts a development user and its settings the first time the database is created.
 * Every user-owned row (logs, history, scheduled workouts) needs a valid users.id.
 */
object DefaultDataSeeder : RoomDatabase.Callback() {

    const val DEFAULT_USER_ID = "00000000-0000-0000-0000-000000000001"

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val now = System.currentTimeMillis()

        db.execSQL(
            """
            INSERT INTO users (
                id, name, weight, height, daily_calorie_goal, daily_water_goal,
                streak_days, total_xp, created_at
            ) VALUES (?, ?, NULL, NULL, 2000, 2500, 0, 0, ?)
            """.trimIndent(),
            arrayOf<Any?>(DEFAULT_USER_ID, "Athlete", now)
        )

        db.execSQL(
            """
            INSERT INTO user_settings (
                user_id, language, font_size, units_system,
                training_reminders, water_reminders
            ) VALUES (?, 'en', 'medium', 'metric', 1, 1)
            """.trimIndent(),
            arrayOf<Any?>(DEFAULT_USER_ID)
        )
    }
}
