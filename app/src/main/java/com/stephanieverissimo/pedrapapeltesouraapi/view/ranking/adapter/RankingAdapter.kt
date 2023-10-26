package com.stephanieverissimo.pedrapapeltesouraapi.view.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stephanieverissimo.pedrapapeltesouraapi.databinding.ItemRankingBinding
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound

class RankingAdapter(private val gameRounds: List<GameRound>) :
    RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameRound = gameRounds[position]
        holder.bind(gameRound)
    }

    override fun getItemCount() = gameRounds.size

    inner class ViewHolder(private val binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gameRound: GameRound) {
            binding.tvPlayerName.text = "Player Name: ${gameRound.playerName}"
            binding.tvOpponentName.text = "Opponent Name: ${gameRound.opponentName}"
            binding.tvResult.text = "Game Result: ${gameRound.result}"
        }
    }
}
