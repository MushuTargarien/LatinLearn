package com.example.latinlearn

data class Affix(
    val type: String,       // "prefix" или "suffix"
    val text: String,       // Например: "bi-"
    val meaning: String,    // Например: "Означает «два»"
    val example: String,    // Например: "bicycle"
    val explanation: String // Например: "bi - «два» + cyclus - круг = велосипед"
)