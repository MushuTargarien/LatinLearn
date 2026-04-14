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



    fun getAnalogiesForTerm(termId: Int): Cursor{
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
                lt.name as typeOfConnection,
                tal.explanation as explanationConnection
            FROM math_terms mt
            INNER JOIN morpheme_math_term mmt ON mt.id = mmt.math_term_id
            INNER JOIN morphemes m ON mmt.morpheme_id = m.id
            INNER JOIN analogies a ON m.id = a.morpheme_id 
            WHERE mt.id = ?
            ORDER BY tal.id ASC
        """, arrayOf(termId.toString()))
    }

    fun getMathTermById(termId: Int):Cursor{
        return database.query(
            DBHelper.TABLE_MATH_TERMS,
            null,
            "${DBHelper.COL_ID_MATH_TERM} = ?",
            arrayOf(termId.toString()),
            null,
            null,
            null
        )
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

    fun getLinkTypeById(linkTypeId: Int): Cursor {
        return database.query(
            DBHelper.TABLE_LINK_TYPES,
            null,
            "${DBHelper.COL_ID_LINK_TYPE} = ?",
            arrayOf(linkTypeId.toString()),
            null,
            null,
            null
        )
    }

    fun getAllLinkTypes(): Cursor{
        return database.query(
            DBHelper.TABLE_LINK_TYPES,
            null,
            null,
            null,
            null,
            null,
            "${DBHelper.COL_ID_LINK_TYPE} ASC"
        )
    }


    // USERS
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