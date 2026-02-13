package com.example.latinlearn

data class Affix(
    val name: String,      // "bi-", "tri-", "-able"...
    val meaning: String,   // "Означает «два»"
    val type: String,      // "prefix" или "suffix"
    val words: List<Word>  // Слова для этого аффикса
)

// Модель слова
data class Word(
    val imageResId: Int,
    val word: String,
    val explanation: String
)