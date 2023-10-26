package com.stephanieverissimo.pedrapapeltesouraapi.view.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stephanieverissimo.pedrapapeltesouraapi.databinding.ItemGamePreviewBinding
import com.stephanieverissimo.pedrapapeltesouraapi.models.GameRound

class GameHistoryPreviewAdapter : RecyclerView.Adapter<GameHistoryPreviewAdapter.ViewHolder>() {

    private var gameRounds: List<GameRound> = listOf()

    class ViewHolder(private val binding: ItemGamePreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gameRound: GameRound) {
            binding.tvPlayerMove.text = gameRound.playerMove
            binding.tvOpponentMove.text = gameRound.opponentMove
        }
    }

    fun setData(newGameRounds: List<GameRound>) {
        gameRounds = newGameRounds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGamePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameRounds[position])
    }

    override fun getItemCount(): Int = gameRounds.size
}
