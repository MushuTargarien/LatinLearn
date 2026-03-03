package com.example.latinlearn

import java.time.zone.ZoneOffsetTransitionRule

data class Affix(
    val id: Int,
    val type: String,
    val affix: String,
    val meaning: String,
    val examples: List<ExampleModel>
)
data class ExampleModel(
    val id: Int,
    val word: String,
    val definition: String,
    val explanation: String,
    val image: String
)


