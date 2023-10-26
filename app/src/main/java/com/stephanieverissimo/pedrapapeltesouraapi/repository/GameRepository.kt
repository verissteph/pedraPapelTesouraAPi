package com.stephanieverissimo.pedrapapeltesouraapi.repository

import com.stephanieverissimo.pedrapapeltesouraapi.api.ApiService
import com.stephanieverissimo.pedrapapeltesouraapi.database.GameRoundDao
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound
import com.stephanieverissimo.pedrapapeltesouraapi.models.RockPaperScissorsResponse

class GameRepository(private val gameRoundDao: GameRoundDao) {
    private val api = ApiService.toysApi

    suspend fun getMedievalName(): String? {
        val response = api.getMedievalName()
        if (response.isSuccessful) {

            return response.body()?.results?.getOrNull(0)
        }
        return null
    }

    suspend fun playGame(playerMove: String): RockPaperScissorsResponse? {
        val response = api.playGame(playerMove)
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception("Error to call API: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun saveGameRound(gameRound: GameRound) {
        gameRoundDao.insert(gameRound)
    }
}

