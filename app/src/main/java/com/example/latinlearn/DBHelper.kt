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

  /* companion object{
         const val DATABASE_NAME = "latin_learn_db"
         const val DATABASE_VERSION = 1

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
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("""
            CREATE TABLE $TABLE_AFFIXES (
                $COL_ID_AFFIX INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COL_TYPE TEXT NOT NULL,
                $COL_AFFIX TEXT NOT NULL,
                $COL_MEANING TEXT NOT NULL)
                """)

        //  таблицы доменов
        db?.execSQL("""
            CREATE TABLE $TABLE_DOMAINS (
                $COL_ID_DOMAIN INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TITLE TEXT NOT NULL)
            """)

        // Создание таблицы примеров
        db?.execSQL("""
            CREATE TABLE $TABLE_EXAMPLES (
                $COL_ID_EXAMPLE INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_ID_AFFIX_FK INTEGER NOT NULL,
                $COL_ID_DOMAIN_FK INTEGER NOT NULL,
                $COL_WORD TEXT NOT NULL,
                $COL_DEFINITION TEXT,
                $COL_EXPLANATION TEXT,
                $COL_IMAGE TEXT,
                FOREIGN KEY ($COL_ID_AFFIX_FK) REFERENCES $TABLE_AFFIXES($COL_ID_AFFIX) ON DELETE CASCADE,
                FOREIGN KEY ($COL_ID_DOMAIN_FK) REFERENCES $TABLE_DOMAINS($COL_ID_DOMAIN) ON DELETE CASCADE)
                """)

        // Создание таблицы пользователей
        db?.execSQL("""
            CREATE TABLE $TABLE_USERS (
                $COL_ID_USER INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_LOGIN TEXT UNIQUE NOT NULL,
                $COL_PASS TEXT NOT NULL,
                $COL_ROLE TEXT NOT NULL)
            """)

        db?.execSQL("PRAGMA foreign_key = ON")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXAMPLES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DOMAINS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXAMPLES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

   */

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