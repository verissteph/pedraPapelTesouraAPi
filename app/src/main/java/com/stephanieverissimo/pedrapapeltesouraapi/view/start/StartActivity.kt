package com.stephanieverissimo.pedrapapeltesouraapi.view.start

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stephanieverissimo.pedrapapeltesouraapi.databinding.ActivityStartBinding
import com.stephanieverissimo.pedrapapeltesouraapi.view.game.GameActivity
import com.stephanieverissimo.pedrapapeltesouraapi.viewmodel.StartViewModel


class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    private val viewModel by viewModels<StartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonStartGame.setOnClickListener {
            val playerName = binding.editTextPlayerName.text.toString().trim()
            if (playerName.isNotEmpty()) {
                viewModel.setPlayerName(playerName)
                startGameActivity()
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("playerName", binding.editTextPlayerName.text.toString())
        startActivity(intent)
    }
}


