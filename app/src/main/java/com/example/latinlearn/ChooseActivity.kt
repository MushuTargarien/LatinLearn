package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ChooseActivity : AppCompatActivity() {

    // Тестовые данные
    private val prefixAffixes = listOf(
        Affix(
            "prefix",
            "bi-",
            "Означает «два»",
            "bicycle",
            "bi - «два» + cyclus - круг = велосипед"
        ),
        Affix(
            "prefix",
            "tri-",
            "Означает «три»",
            "triangle",
            "tri - «три» + angle = треугольник"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        ),
        Affix(
            "prefix",
            "uni-",
            "Означает «один»",
            "unicycle",
            "uni - «один» + cycle = одноколёсный велосипед"
        )

    )

    private val suffixAffixes = listOf(
        Affix(
            "suffix",
            "-able",
            "Возможно",
            "comfortable",
            "-able - прилагательное, означающее «возможно»"
        ),
        Affix(
            "suffix",
            "-ness",
            "Состояние",
            "happiness",
            "-ness - состояние, качество"
        ),
        Affix(
            "suffix",
            "-cy",
            "Место",
            "privacy",
            "-cy - место, связь с чем-то"
        )
    )
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        recyclerView.adapter = AffixAdapter(prefixAffixes) { affix ->
            // Передаём данные в MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("AFFIX_TEXT", affix.text)
                putExtra("AFFIX_MEANING", affix.meaning)
                putExtra("AFFIX_EXAMPLE", affix.example)
                putExtra("AFFIX_EXPLANATION", affix.explanation)
            }
            startActivity(intent)
        }

        val buttonToMainMenu: Button = findViewById(R.id.btn_to_main_menu)
        buttonToMainMenu.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }



        // Настройка Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            listOf("Приставки", "Суффиксы")
        ).apply {
            setDropDownViewResource(R.layout.spinner_dropdown_item)
        }
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val affixes = if (position == 0) prefixAffixes else suffixAffixes
                recyclerView.adapter = AffixAdapter(affixes) { affix ->
                    val intent = Intent(this@ChooseActivity, MainActivity::class.java).apply {
                        putExtra("AFFIX_TEXT", affix.text)
                        putExtra("AFFIX_MEANING", affix.meaning)
                        putExtra("AFFIX_EXAMPLE", affix.example)
                        putExtra("AFFIX_EXPLANATION", affix.explanation)
                    }
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}