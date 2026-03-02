package com.example.latinlearn

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.time.zone.ZoneOffsetTransitionRule
import kotlin.math.exp

class DBManager(context: Context) {
    private val dbHelper = DBHelper(context)
    private val database: SQLiteDatabase = dbHelper.writableDatabase

    // AFFIXES
    fun addAffix(type: String, affix: String, meaning: String): Long{
        val values = ContentValues().apply {
            put(DBHelper.COL_TYPE, type)
            put(DBHelper.COL_AFFIX, affix)
            put(DBHelper.COL_MEANING, meaning)
        }
        return database.insert(DBHelper.TABLE_AFFIXES,null,values)
    }

    fun getAllAffixes(): Cursor{
        return database.query(
            DBHelper.TABLE_AFFIXES,
            null,
            null,
            null,
            null,
            null,
            "${DBHelper.COL_ID_AFFIX} ASC"
        )
    }

    fun getAffixesByType(type: String): Cursor{
        return  database.query(
            DBHelper.TABLE_AFFIXES,
            null,
            "${DBHelper.COL_TYPE}=?",
            arrayOf(type),
            null,
            null,
            null
        )
    }


    //DOMAINS
    fun addDomain(title: String): Long{
        val values = ContentValues().apply {
            put(DBHelper.COL_TITLE, title)
        }
        return database.insert(DBHelper.TABLE_DOMAINS,null,values)
    }

    fun getAllDomains(): Cursor{
        return database.query(
            DBHelper.TABLE_DOMAINS,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }


    //EXAMPLES
    fun addExample(affixID: Int, domainID: Int, word: String, definition: String, explanation: String, image: String): Long{
        val values = ContentValues().apply {
            put(DBHelper.COL_ID_AFFIX_FK, affixID)
            put(DBHelper.COL_ID_DOMAIN_FK, domainID)
            put(DBHelper.COL_WORD, word)
            put(DBHelper.COL_DEFINITION, definition)
            put(DBHelper.COL_EXPLANATION, explanation)
            put(DBHelper.COL_IMAGE, image)
        }
        return database.insert(DBHelper.TABLE_EXAMPLES,null,values)
    }

    fun getExamplesByAffixId(affixId: Int): Cursor{
        return database.query(
            DBHelper.TABLE_EXAMPLES,
            null,
            "${DBHelper.COL_ID_AFFIX_FK}=?",
            arrayOf(affixId.toString()),
            null,
            null,
            null
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