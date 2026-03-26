package com.example.latinlearn
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

data class MathTerm(
    val id: Int,
    val term: String,
    val definition: String
)

data class Analogy(
    val id: Int,
    val word: String,
    val translate: String,
    val explanation: String,
    val image_url: String
)

data class TermWithAnalogy (
    val termId: Int,
    val term: String,
    val termTranslate: String,
    val definition: String,
    val analogyWord: String,
    val analogyTranslate: String,
    val analogyDefinition: String,
    val typeOfConnection: String,
    val explanationConnection: String,
    val imageUrl: String
)