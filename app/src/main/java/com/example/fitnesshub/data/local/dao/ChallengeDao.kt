package com.example.fitnesshub.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnesshub.data.local.entity.Challenge
import com.example.fitnesshub.data.local.entity.UserChallenge
import com.example.fitnesshub.data.local.relation.ChallengeWithProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenges ORDER BY title")
    fun observeAll(): Flow<List<Challenge>>

    @Query(
        """
        SELECT c.*,
               COALESCE(uc.progress_current, 0) AS progress_current,
               COALESCE(uc.is_completed, 0) AS is_completed
        FROM challenges c
        LEFT JOIN user_challenges uc
            ON uc.challenge_id = c.id AND uc.user_id = :userId
        ORDER BY c.title
        """
    )
    fun observeForUser(userId: String): Flow<List<ChallengeWithProgress>>

    @Query("SELECT * FROM user_challenges WHERE user_id = :userId")
    fun observeUserChallenges(userId: String): Flow<List<UserChallenge>>

    @Query("SELECT * FROM challenges WHERE id = :challengeId")
    suspend fun getChallenge(challengeId: String): Challenge?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertChallenge(challenge: Challenge)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertChallenges(challenges: List<Challenge>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: UserChallenge)

    @Query(
        "UPDATE user_challenges SET progress_current = :progress, is_completed = :isCompleted " +
            "WHERE user_id = :userId AND challenge_id = :challengeId"
    )
    suspend fun updateProgress(
        userId: String,
        challengeId: String,
        progress: Int,
        isCompleted: Boolean
    )

    @Delete
    suspend fun deleteChallenge(challenge: Challenge)
}
