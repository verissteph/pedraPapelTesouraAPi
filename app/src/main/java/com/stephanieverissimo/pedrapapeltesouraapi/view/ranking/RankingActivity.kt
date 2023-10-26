package com.stephanieverissimo.pedrapapeltesouraapi.view.ranking

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephanieverissimo.pedrapapeltesouraapi.databinding.ActivityRankingBinding
import com.stephanieverissimo.pedrapapeltesouraapi.view.ranking.adapter.RankingAdapter
import com.stephanieverissimo.pedrapapeltesouraapi.viewmodel.RankingViewModel

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRanking.layoutManager = LinearLayoutManager(this)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupObservers()
        fetchGameHistory()
    }

    private fun setupObservers() {
        viewModel.gameHistory.observe(this) { rounds ->
             val adapter = RankingAdapter(rounds)
            binding.rvRanking.adapter = adapter

        }


    }
    private fun fetchGameHistory(){
        viewModel.fetchGameHistory()
    }


}
