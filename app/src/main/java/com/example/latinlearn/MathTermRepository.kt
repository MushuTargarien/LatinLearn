package com.example.latinlearn

import android.content.Context

class MathTermRepository(private val context: Context) {
    private val dbManager by lazy { DBManager(context) }

    fun getAllTermWithAnalogies(mode: AnalogyMode): List<MathTermWithAnalogies> {
        val terms = mutableListOf<MathTermWithAnalogies>()
        val cursor = dbManager.getMathTermsForList()

        if (cursor.moveToFirst()){
            do {
                val termId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val term = cursor.getString(cursor.getColumnIndexOrThrow("term")).orEmpty()
                val translate = cursor.getString(cursor.getColumnIndexOrThrow("translate")).orEmpty()
                val analogies = getAnalogiesForTerm(termId, mode)

                terms.add(MathTermWithAnalogies(
                    termId = termId,
                    term = term,
                    translate = translate,
                    analogies = analogies,
                    isExpanded = false
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return terms
    }


    private fun getAnalogiesForTerm(termId: Int, mode: AnalogyMode): List<AnalogyItem> {
        val analogies = mutableListOf<AnalogyItem>()
        val cursor = dbManager.getAnalogiesForList(termId, mode)

        if(cursor.moveToFirst()) {
            do {
                analogies.add(AnalogyItem(
                    word = cursor.getString(cursor.getColumnIndexOrThrow("analogyWord")).orEmpty(),
                    translate = cursor.getString(cursor.getColumnIndexOrThrow("analogyTranslate")).orEmpty(),
                    morphemeForm = cursor.getString(cursor.getColumnIndexOrThrow("morphemeForm")).orEmpty()
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return  analogies
    }

    fun close() {
        dbManager.close()
    }
}