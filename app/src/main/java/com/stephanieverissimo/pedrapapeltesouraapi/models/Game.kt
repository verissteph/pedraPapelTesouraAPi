package com.stephanieverissimo.pedrapapeltesouraapi.models

import com.google.gson.annotations.SerializedName

data class RockPaperScissorsResponse(
    @SerializedName("cpu") val cpu: String?,
    @SerializedName("player") val player: String?,
    @SerializedName("winner") val winner: String?,
    @SerializedName("move") val move: String?
)



