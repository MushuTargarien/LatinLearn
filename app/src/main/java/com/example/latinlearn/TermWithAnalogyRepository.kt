package com.example.latinlearn

import android.content.Context

class TermWithAnalogyRepository(private val context: Context){
    private val dbManager by lazy { DBManager(context) }

    fun getAnalogiesForTerm(termId: Int): List<TermWithAnalogy>{
        val analogies = mutableListOf<TermWithAnalogy>()
        val cursor = dbManager.getAnalogiesForTerm(termId)

        if (cursor.moveToFirst()){
            do {
                val termWithAnalogy = TermWithAnalogy(
                    termId = cursor.getInt(cursor.getColumnIndexOrThrow("termId")),
                    term = cursor.getString(cursor.getColumnIndexOrThrow("term")),
                    termTranslate = cursor.getString(cursor.getColumnIndexOrThrow("termTranslate")),
                    definition = cursor.getString(cursor.getColumnIndexOrThrow("definition")),
                    analogyWord = cursor.getString(cursor.getColumnIndexOrThrow("analogyWord")),
                    analogyTranslate = cursor.getString(cursor.getColumnIndexOrThrow("analogyTranslate")),
                    analogyDefinition = cursor.getString(cursor.getColumnIndexOrThrow("analogyDefinition")),
                    typeOfConnection = cursor.getString(cursor.getColumnIndexOrThrow("typeOfConnection")),
                    explanationConnection = cursor.getString(cursor.getColumnIndexOrThrow("explanationConnection")),
                    imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("imageUrl")) ?: ""
                )
                analogies.add(termWithAnalogy)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return analogies
    }

    fun getAllMathTerms(): List<MathTerm> {
        val terms = mutableListOf<MathTerm>()
        val cursor = dbManager.getAllMathTerms()

        if (cursor.moveToFirst()) {
            do{
                val term = MathTerm(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    term = cursor.getString(cursor.getColumnIndexOrThrow("term")),
                    translate = cursor.getString(cursor.getColumnIndexOrThrow("translate")),
                    definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"))
                )
                terms.add(term)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return terms
    }

    fun close(){
        dbManager.close()
    }
}


    /*
        fun getAnalogiesForTerm(termId: Int): List<TermWithAnalogy>{
            return when (termId){
                1 -> listOf(
                    TermWithAnalogy(
                        termId = 1,
                        term = "binary",
                        termTranslate = "Бинарный",
                        definition = "Связанный с двумя элементами или операциями (например, бинарная операция: сложение, умножение)",
                        analogyWord = "Bicycle",
                        analogyTranslate = "Велосипед",
                        analogyDefinition = "Двухколёсное транспортное средство",
                        typeOfConnection = "Морфемная связь",
                        explanationConnection = "«bi-» (два) + «cycle» (колесо) = двухколёсное транспортное средство",
                        imageUrl = "https://i.ibb.co/mVnFg2z9/bicycle.png"
                    ),
                    TermWithAnalogy(
                        termId = 1,
                        term = "binary",
                        termTranslate = "Бинарный",
                        definition = "Связанный с двумя элементами или операциями (например, бинарная операция: сложение, умножение)",
                        analogyWord = "Dual",
                        analogyTranslate = "Двойной",
                        analogyDefinition = "Состоящий из двух частей или элементов",
                        typeOfConnection = "Семантическая связь",
                        explanationConnection = "Оба слова означают «состоящий из двух частей». Dual используется в философии (дуализм), binary — в математике (двоичная система)",
                        imageUrl = "https://i.ibb.co/mVnFg2z9/bicycle.png"
                    ),
                    TermWithAnalogy(
                        termId = 1,
                        term = "binary",
                        termTranslate = "Бинарный",
                        definition = "Связанный с двумя элементами или операциями (например, бинарная операция: сложение, умножение)",
                        analogyWord = "Ternary",
                        analogyTranslate = "Троичный",
                        analogyDefinition = "Состоящий из трёх элементов или частей",
                        typeOfConnection = "Структурная связь",
                        explanationConnection = "Оба слова построены по паттерну: [число] + «-nary». Binary = bi- (два) + -nary, Ternary = ter- (три) + -nary",
                        imageUrl = "https://i.ibb.co/mVnFg2z9/bicycle.png"
                    )
                )
                else -> emptyList()
            }
        }


        fun getTermWithAnalogy(termId: Int): TermWithAnalogy{
            val term = getTermById(termId)
            val analogy = getAnalogyByTermId(termId)
            return TermWithAnalogy(
                termId = term.id,
                term = term.term,
                definition = term.definition,
                analogyWord = analogy.word,
                analogyTranslate = analogy.translate,
                analogyExplantion = analogy.explanation,
                imageUrl = analogy.image_url

            )
        }

        private fun getTermById(termId: Int): MathTerm{
            val cursor = dbManager.getTermById(termId)
            cursor.moveToFirst()
            return MathTerm(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                term = cursor.getString(cursor.getCilunOrThrow("term")),
                definition = cursor.getString(cursor.getColumnOrThrow("definition"))
            ).also { cursor.close() }
        }

        private fun gerAnalogyByTermId(termId: Int): Analogy{
            val cursor = dbManager.getAnalogyTermId(termId)
            cursor.moveToFirst()
            return Analogy(
                id = cursor.getInt(cursor.getColumnIndexorThrow("id")),
                word = cursor.getString(cursor.getColumnIndexorThrow("word")),
                translate = cursor.getString(cursor.getColumnIndexorThrow("translate")),
                explanation = cursor.getString(cursor.getColumnIndexorThrow("explanation")),
                image_url = cursor.getString(cursor.getColumnIndexorThrow("image_url"))
            ).also { cursor.close() }
        }

     */

