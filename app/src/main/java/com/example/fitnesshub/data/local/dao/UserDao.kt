package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.fitnesshub.data.local.entity.User
import com.example.fitnesshub.data.local.relation.UserWithSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUser(userId: String): Flow<User?>

    @Query("SELECT * FROM users LIMIT 1")
    fun observeFirstUser(): Flow<User?>

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: String): User?

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeWithSettings(userId: String): Flow<UserWithSettings?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("UPDATE users SET streak_days = :streakDays WHERE id = :userId")
    suspend fun updateStreak(userId: String, streakDays: Int)

    @Query("UPDATE users SET total_xp = :totalXp WHERE id = :userId")
    suspend fun updateTotalXp(userId: String, totalXp: Int)
}
