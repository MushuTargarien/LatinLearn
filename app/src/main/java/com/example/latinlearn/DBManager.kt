package com.example.latinlearn

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.time.zone.ZoneOffsetTransitionRule
import kotlin.math.exp

/*
*   класс с запросами в БД
*/

class DBManager(context: Context) {
    private val dbHelper = DBHelper(context)
    private val database: SQLiteDatabase = dbHelper.writableDatabase



    fun getAnalogiesForTerm(termId: Int, mode: AnalogyMode): Cursor{
        return database.rawQuery("""
            SELECT
                mt.id as termId,
                mt.term as term,
                mt.translate as termTranslate,
                mt.definition as definition,
                a.word as analogyWord,
                a.translate as analogyTranslate,
                a.definition as analogyDefinition,
                a.image_url as imageUrl,
                a.explanation as morphemeExplanation,
                m.form as morphemeForm,
                m.type as morphemeType,
                m.meaning as morphemeMeaning
            FROM math_terms mt
            INNER JOIN morpheme_math_term mmt ON mt.id = mmt.math_term_id
            INNER JOIN morphemes m ON mmt.morpheme_id = m.id
            INNER JOIN analogies a ON m.id = a.morpheme_id 
            WHERE mt.id = ?
            AND a.is_math_analogy = ?
            ORDER BY a.id ASC
        """, arrayOf(termId.toString(),if (mode.isMathAnalogy) "1" else "0"))
    }

    fun getAnalogiesForList(termId: Int, mode: AnalogyMode): Cursor{
        return database.rawQuery("""
            SELECT
                a.word as analogyWord,
                a.translate as analogyTranslate,
                m.form as morphemeForm
            FROM analogies a
            INNER JOIN morphemes m ON a.morpheme_id = m.id
            INNER JOIN morpheme_math_term mmt ON m.id = mmt.morpheme_id
            WHERE mmt.math_term_id = ?
            AND a.is_math_analogy = ?
            ORDER BY a.id ASC
        """, arrayOf(termId.toString(),if (mode.isMathAnalogy) "1" else "0"))
    }



    fun getAllMathTerms(): Cursor{
        return database.query(
            DBHelper.TABLE_MATH_TERMS,
            null,
            null,
            null,
            null,
            null,
            "${DBHelper.COL_ID_MATH_TERM} ASC"
        )
    }

    fun getMathTermsForList(): Cursor{
        return database.query(
            DBHelper.TABLE_MATH_TERMS,
            arrayOf("id", "term", "translate"),
            null,
            null,
            null,
            null,
            "${DBHelper.COL_ID_MATH_TERM} ASC"
        )
    }




    fun authenticate(login: String,pass: String): Cursor{
        return database.query(
            DBHelper.TABLE_USERS,
            null,
            "${DBHelper.COL_LOGIN}=? AND ${DBHelper.COL_PASS}=?",
            arrayOf(login,pass),
            null,
            null,
            null
        )
    }

    fun close(){
        dbHelper.close()
    }
}