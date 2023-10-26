package com.stephanieverissimo.pedrapapeltesouraapi.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stephanieverissimo.pedrapapeltesouraapi.database.AppDatabase
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound
import kotlinx.coroutines.launch

class RankingViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRoundDao = AppDatabase.getDatabase(application).gameRoundDao()
    private val _gameHistory = MutableLiveData<List<GameRound>>()
    val gameHistory: LiveData<List<GameRound>> get() = _gameHistory

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchGameHistory() {
        viewModelScope.launch {
            try {
                val history = gameRoundDao.getAllGameRounds()
                Log.d("HISTORY", history.toString())

                _gameHistory.postValue(history)
            } catch (e: Exception) {
                _error.postValue("Error retrieving game history.")
            }
        }
    }
}
