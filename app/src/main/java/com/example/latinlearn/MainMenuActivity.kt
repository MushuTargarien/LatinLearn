package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainMenuActivity : AppCompatActivity() {

    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        dbManager = DBManager(this) // инициализация БД
       // addTestData();

        val buttonContinue: Button = findViewById(R.id.btn_continue)
        buttonContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

  /*  private fun addTestData(){
        val bi = dbManager.addAffix("prefix","bi-","Означает <Два>")
        val tri = dbManager.addAffix("prefix","tri-","Означает <три>")
        val uni = dbManager.addAffix("prefix","ini-","Означает <один>")
        val ableId = dbManager.addAffix("suffix","-able","Означает <способность>")

        val general = dbManager.addDomain("Общее")
        val science = dbManager.addDomain("Наука")


        dbManager.addExample(
            affixID = bi.toInt(),
            domainID = general.toInt(),
            word = "bicycle",
            definition = "Велосипед",
            explanation = "bi (два) + cyclus (круг)",
            image = "")

    }
    */

}