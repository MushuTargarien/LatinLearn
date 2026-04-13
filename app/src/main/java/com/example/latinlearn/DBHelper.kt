package com.example.latinlearn

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.File
import java.io.IOException

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{

        //таблица пользователей
        const val TABLE_USERS = "users"
        const val COL_ID_USER = "userID"
        const val COL_LOGIN = "login"
        const val COL_PASS = "pass"
        const val COL_ROLE = "role"


        const val TABLE_MATH_TERMS = "math_terms"
        const val COL_ID_MATH_TERM = "id"
        const val COL_TERM = "term"
        const val COL_TERM_TRANSLATE = "translate"
        const val COL_TERM_DEFINITION = "definition"

        const val TABLE_ANALOGIES = "analogies"
        const val COL_ID_ANALOGY = "id"
        const val COL_ANALOGY_WORD = "word"
        const val COL_ANALOGY_TRANSLATE = "translate"
        const val COL_ANALOGY_DEFINITION = "definition"
        const val COL_ANALOGY_MORPHEME_ID_FK = "morpheme_id"
        const val COL_ANALOGY_EXPLANATION = "explanation"
        const val  COL_ANALOGY_IMAGE_URL = "image_url"

        const val TABLE_LINK_TYPES = "link_types"
        const val COL_ID_LINK_TYPE = "id"
        const val COL_ID_LINK_NAME = "name"


        const val TABLE_MORPHEMES = "morphemes"
        const val COL_ID_MORPHEME = "id"
        const val COL_MORPHEME_FORM = "form"
        const val COL_MORPHEME_TYPE = "type"
        const val COL_MORPHEME_MEANING = "meaning"

        const val TABLE_MORPHEME_MATH_TERM = "morpheme_math_term"
        const val COL_MORPHEME_ID_FK = "morpheme_id"
        const val COL_MATH_TERM_ID_FK = "math_term_id"
        const val COL_MORPHEME_MATH_TERM_EXPLANATION = "explanation"



        const val DATABASE_NAME = "latin_learn2"
        const val DATABASE_VERSION = 1
        private const val DB_PATH_SUFFIX = "/databases/"
        private var instance: DBHelper? = null

        fun getInstance(context: Context): DBHelper{
            return instance ?: synchronized(this){
                DBHelper(context.applicationContext).also { instance= it }
            }
        }
    }
    init {
        if (!isDatabaseExists(context)){
            copyDatabaseFromAssets(context)
        }
    }

    private fun isDatabaseExists(context: Context): Boolean{
        var dbFile = context.getDatabasePath(DATABASE_NAME)
        return dbFile.exists()
    }


    //
    // загрузка БД из папки assets
    //
    private fun copyDatabaseFromAssets(context: Context){
        try{
            val inputStream: InputStream = context.assets.open("databases/$DATABASE_NAME.db")

            val dbFile = context.getDatabasePath(DATABASE_NAME)
            val parentDir = dbFile.parentFile
            parentDir?.mkdirs()

            val outputStream = FileOutputStream(dbFile)
            val buffer = ByteArray(8192)
            var length: Int
            while (inputStream.read(buffer).also {length = it} > 0){
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: IOException){
            android.util.Log.e("DB_HELPER","ошибка копирования БД", e)
            throw RuntimeException("Не удалось скопировать БД из assets", e)
        }
    }
    override fun onCreate(db: SQLiteDatabase?) = Unit

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) = Unit

    fun getReadableDatabases(): SQLiteDatabase {
        return  super.getReadableDatabase()
    }

    fun getWritableDatabases(): SQLiteDatabase {
        return  super.getWritableDatabase()
    }




}