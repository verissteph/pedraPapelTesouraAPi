package com.stephanieverissimo.pedrapapeltesouraapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound
import com.stephanieverissimo.pedrapapeltesouraapi.models.RockPaperScissorsResponse
import com.stephanieverissimo.pedrapapeltesouraapi.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(private val repository: GameRepository) : ViewModel() {

    private val _opponentName = MutableLiveData<String>()
    val opponentName: LiveData<String> get() = _opponentName

    private val _opponentMove = MutableLiveData<String?>()
    val opponentMove: MutableLiveData<String?> get() = _opponentMove



    private val _gameResult: MutableLiveData<String?> = MutableLiveData("Waiting for move")
    val gameResult: MutableLiveData<String?> get() = _gameResult


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _tempGameResult = MutableLiveData<RockPaperScissorsResponse?>()
    val tempGameResult: LiveData<RockPaperScissorsResponse?> get() = _tempGameResult


    private val _tempGameHistory = MutableLiveData<List<GameRound>>()
    val tempGameHistory: LiveData<List<GameRound>> get() = _tempGameHistory


    fun fetchOpponentName() {
        viewModelScope.launch {
            val name = repository.getMedievalName()
            _opponentName.postValue(name ?: "Erro ao buscar nome")
        }
    }


    fun playGame(move: String) {
        viewModelScope.launch {
            try {
                val gameData = repository.playGame(move)
                _tempGameResult.postValue(gameData)
                _gameResult.postValue(gameData?.winner)
                _opponentMove.postValue(gameData?.cpu)

            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }


    fun saveGameRound(
        playerName: String,
        opponentName: String,
        playerMove: String,
        opponentMove: String,
        result: String
    ) {
        viewModelScope.launch {
            val gameRound = GameRound(
                playerName = playerName,
                opponentName = opponentName,
                playerMove = playerMove,
                opponentMove = opponentMove,
                result = result
            )
            repository.saveGameRound(gameRound)

            val currentHistory = _tempGameHistory.value?.toMutableList() ?: mutableListOf()
            currentHistory.add(gameRound)
            if (currentHistory.size > 3) {
                currentHistory.removeAt(0)
            }
            _tempGameHistory.postValue(currentHistory)
        }
    }
}






