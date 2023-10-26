package com.stephanieverissimo.pedrapapeltesouraapi.api

import com.stephanieverissimo.pedrapapeltesouraapi.models.MedievalNameResponse
import com.stephanieverissimo.pedrapapeltesouraapi.models.RockPaperScissorsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ToysApi {
    @GET("medieval_name")
    suspend fun getMedievalName(): Response<MedievalNameResponse>

    @GET("rock_paper_scissors")
    suspend fun playGame(@Query("guess") move: String): Response<RockPaperScissorsResponse>

}


