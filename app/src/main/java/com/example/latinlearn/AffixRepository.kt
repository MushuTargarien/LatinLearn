package com.example.latinlearn

import android.content.Context
import com.example.latinlearn.DBManager
import com.example.latinlearn.DBHelper
import com.example.latinlearn.Affix
import com.example.latinlearn.ExampleModel

class AffixRepository(private val context: Context) {

    private val dbManager by lazy { DBManager(context) }

    fun getAllAffixes(): List<Affix>{
        val affixes = mutableListOf<Affix>()

        val affixCursor = dbManager.getAllAffixes()
        if ( affixCursor.moveToFirst()){
            do{
                val id = affixCursor.getInt(affixCursor.getColumnIndexOrThrow("affixID"))
                val type = affixCursor.getString(affixCursor.getColumnIndexOrThrow("type"))
                val affix = affixCursor.getString(affixCursor.getColumnIndexOrThrow("affix"))
                val meaning = affixCursor.getString(affixCursor.getColumnIndexOrThrow("meaning"))
                val examples = getExamplesbyAffixId(id)

                affixes.add(Affix(id,type, affix, meaning,examples))
            } while(affixCursor.moveToNext())
        }
        affixCursor.close()
        return affixes
    }

    private fun getExamplesbyAffixId(affixId: Int): List<ExampleModel> {
        val examples = mutableListOf<ExampleModel>()
        val exampleCursor = dbManager.getExamplesByAffixId(affixId)
        if(exampleCursor.moveToFirst()){
            do {
               val id = exampleCursor.getInt(exampleCursor.getColumnIndexOrThrow("exampleID"))
               val word = exampleCursor.getString(exampleCursor.getColumnIndexOrThrow("word"))
               val definition = exampleCursor.getString(exampleCursor.getColumnIndexOrThrow("definition"))
               val explanation = exampleCursor.getString(exampleCursor.getColumnIndexOrThrow("explanation"))
               val image = exampleCursor.getString(exampleCursor.getColumnIndexOrThrow("image"))

                examples.add(ExampleModel(id,word,definition,explanation,image))
            } while (exampleCursor.moveToNext())
        }
        exampleCursor.close()
        return examples
    }

    fun close(){
        dbManager.close()
    }
}