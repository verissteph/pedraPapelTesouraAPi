package com.stephanieverissimo.pedrapapeltesouraapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {
    private val _playerName = MutableLiveData<String>()
    val playerName: LiveData<String> get() = _playerName

    fun setPlayerName(name: String) {
        _playerName.value = name
    }
}

