package com.stephanieverissimo.pedrapapeltesouraapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound

@Dao
interface GameRoundDao {
    @Query("SELECT * FROM game_rounds ORDER BY id DESC")
    suspend fun getAllGameRounds(): List<GameRound>

    @Insert
    suspend fun insert(gameRound: GameRound)

}

