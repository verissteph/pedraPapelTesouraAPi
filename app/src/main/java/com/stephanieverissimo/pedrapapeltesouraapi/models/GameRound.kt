package com.stephanieverissimo.pedrapapeltesouraapi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_rounds")
data class GameRound(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playerName: String,
    val opponentName: String,
    val playerMove: String,
    val opponentMove: String,
    val result: String
)


