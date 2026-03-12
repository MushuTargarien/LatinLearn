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
        //таблица аффиксов
        const val TABLE_AFFIXES = "affixes"
        const val COL_ID_AFFIX = "affixID"
        const val COL_TYPE = "type"
        const val COL_AFFIX = "affix"
        const val COL_MEANING = "meaning"

        //таблица областей
        const val TABLE_DOMAINS = "domains"
        const val COL_ID_DOMAIN = "domainID"
        const val COL_TITLE = "title"

        //таблица примеров
        const val TABLE_EXAMPLES = "examples"
        const val COL_ID_EXAMPLE = "exampleID"
        const val COL_ID_AFFIX_FK = "affixID"
        const val COL_ID_DOMAIN_FK = "domainID"
        const val COL_WORD = "word"
        const val COL_DEFINITION = "definition"
        const val COL_EXPLANATION = "explanation"
        const val COL_IMAGE = "image"

        //таблица пользователей
        const val TABLE_USERS = "users"
        const val COL_ID_USER = "userID"
        const val COL_LOGIN = "login"
        const val COL_PASS = "pass"
        const val COL_ROLE = "role"


        const val DATABASE_NAME = "latin_learn"
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