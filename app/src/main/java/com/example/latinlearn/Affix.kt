package com.example.latinlearn

data class Affix(
    val name: String,
    val meaning: String,
    val type: String,
    val words: List<Word>
)


data class Word(
    val imageResId: Int,
    val word: String,
    val explanation: String
)