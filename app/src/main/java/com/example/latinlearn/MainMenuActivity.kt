package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.core.view.WindowCompat;



/*
* Активи Главного меню
 */
class MainMenuActivity : AppCompatActivity() {

    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        setContentView(R.layout.activity_main_menu)

        dbManager = DBManager(this)



        val btnAllTerms: Button = findViewById(R.id.btn_choose_menu)
        btnAllTerms.setOnClickListener {
            val intent = Intent(this, ChooseMenuActivity::class.java)
            startActivity(intent)
        }


    }

}