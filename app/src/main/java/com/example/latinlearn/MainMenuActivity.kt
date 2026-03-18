package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast


/*
* Активи Главного меню
 */
class MainMenuActivity : AppCompatActivity() {

    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        dbManager = DBManager(this) // инициализация БД

        val buttonContinue: Button = findViewById(R.id.btn_continue)
        buttonContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnAllTerms: Button = findViewById(R.id.btn_choose_menu)
        btnAllTerms.setOnClickListener {
            val intent = Intent(this, ChooseMenuActivity::class.java)
            startActivity(intent)
        }


    }

}