package com.stephanieverissimo.pedrapapeltesouraapi.view.game

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephanieverissimo.pedrapapeltesouraapi.database.AppDatabase
import com.stephanieverissimo.pedrapapeltesouraapi.databinding.ActivityGameBinding
import com.stephanieverissimo.pedrapapeltesouraapi.repository.GameRepository
import com.stephanieverissimo.pedrapapeltesouraapi.view.game.adapter.GameHistoryPreviewAdapter
import com.stephanieverissimo.pedrapapeltesouraapi.view.ranking.RankingActivity
import com.stephanieverissimo.pedrapapeltesouraapi.viewmodel.GameViewModel
import com.stephanieverissimo.pedrapapeltesouraapi.viewmodel.GameViewModelFactory

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val dao by lazy { AppDatabase.getDatabase(this).gameRoundDao() }
    private val gameRepository by lazy { GameRepository(dao) }
    private val viewModelFactory by lazy { GameViewModelFactory(gameRepository) }
    private val viewModel by viewModels<GameViewModel> { viewModelFactory }
    private var playerMove: String = ""
    private lateinit var playerName: String
    private val gameHistoryList = mutableListOf<Pair<String, String>>()
    private lateinit var gameHistoryAdapter: GameHistoryPreviewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupObservers()
        viewModel.fetchOpponentName()

        binding.toolbarGame.setNavigationOnClickListener {
            finish()
        }

        playerName = intent?.getStringExtra("playerName") ?: "Unkown Name"

        gameHistoryAdapter = GameHistoryPreviewAdapter()
        binding.rvGameHistoryPreview.adapter = gameHistoryAdapter
        binding.rvGameHistoryPreview.layoutManager = LinearLayoutManager(this)
    }


    private fun setupListeners() {
        binding.btnRock.setOnClickListener {
            playerMove = "rock"
        }
        binding.btnPaper.setOnClickListener {
            playerMove = "paper"
        }
        binding.btnScissors.setOnClickListener {
            playerMove = "scissors"
        }

        binding.btnPlay.setOnClickListener {
            playerMove.let { move ->
                if (gameHistoryList.size < 4) {
                    viewModel.playGame(move)
                } else {
                    Toast.makeText(this, "reached 3 moves", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnGoToHistory.setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }


    }

    private fun setupObservers() {
        viewModel.opponentName.observe(this) { name ->
            binding.tvOpponentName.text = name ?: "Unknow Opponent"
        }


        viewModel.gameResult.observe(this) { result ->
            binding.tvResult.text = "Winner: $result"

            playerMove.let { playerMove ->
                val opponentMove = viewModel.opponentMove.value ?: "Unknow"
                gameHistoryList.add(Pair(playerMove, opponentMove))
                gameHistoryAdapter.notifyDataSetChanged()

            }
            if (gameHistoryList.size > 3) {
                binding.btnRock.isEnabled = false
                binding.btnPaper.isEnabled = false
                binding.btnScissors.isEnabled = false
                binding.btnPlay.isEnabled = false
            }

        }


        viewModel.tempGameResult.observe(this) { gameData ->
            viewModel.fetchOpponentName()
            playerMove.let { move ->
                gameData?.let {
                    viewModel.saveGameRound(
                        playerName,
                        viewModel.opponentName.value ?: "Unknown",
                        move,
                        gameData.cpu ?: "Unknown",
                        gameData.winner ?: "Unknown"
                    )
                }
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }


        viewModel.tempGameHistory.observe(this) { gameRounds ->
            gameHistoryAdapter.setData(gameRounds)
        }

    }
}

