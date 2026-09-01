package com.example.harry_potter_quiz.model

import com.example.harry_potter_quiz.screen.House

data class Personagem (
    val fullName: String,
    val hogwartsHouse: String,
    val image: String
) {
    fun getHouse(): House {
        return when(hogwartsHouse) {
            "Gryffindor", "Grifinória" -> House.GRYFFINDOR
            "Hufflepuff", "Lufa-Lufa", "Lufa Lufa" -> House.HUFFLEPUFF
            "Ravenclaw", "Corvinal" -> House.RAVENCLAW
            "Slytherin", "Sonserina" -> House.SLYTHERIN
            else -> House.GRYFFINDOR
        }
    }
}
