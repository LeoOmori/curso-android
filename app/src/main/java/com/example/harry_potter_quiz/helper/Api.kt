package com.example.harry_potter_quiz.helper

import com.example.harry_potter_quiz.model.Personagem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pt/characters")
    suspend fun getCharacter(
        @Query("index") index: Int
    ): Personagem
}