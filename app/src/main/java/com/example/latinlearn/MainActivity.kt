package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button



class MainActivity : AppCompatActivity() {

    private val affixes = listOf(
        Affix(
            name = "bi-",
            meaning = "Означает «два»",
            type = "prefix",
            words = listOf(
                Word(
                    imageResId = R.drawable.bicycle,
                    word = "bicycle",
                    explanation = "bi (два) + cyclus (круг) = велосипед"
                ),
                Word(
                    imageResId = R.drawable.binocular,
                    word = "binocular",
                    explanation = "bi (два) + ennial (год) = двухлетний"
                ),
                Word(
                    imageResId = R.drawable.bicycle,
                    word = "binocave",
                    explanation = "bi (два) + furca (вилка) = двухвогнутая"
                )
            )
        ),
        Affix(
            name = "tri-",
            meaning = "Означает «три»",
            type = "prefix",
            words = listOf(
                Word(
                    imageResId = R.drawable.binocular,
                    word = "triangle",
                    explanation = "tri (три) + angle (угол) = треугольник"
                ),
                Word(
                    imageResId = R.drawable.binocular,
                    word = "tricycle",
                    explanation = "tri (три) + cycle (колесо) = трёхколёсный велосипед"
                )
            )
        ),
        Affix(
            name = "uni-",
            meaning = "Означает «один»",
            type = "prefix",
            words = listOf(
                Word(
                    imageResId = R.drawable.bicycle,
                    word = "unicorn",
                    explanation = "uni (один) + cornu (рог) = единорог"
                ),
                Word(
                    imageResId = R.drawable.bicycle,
                    word = "unicycle",
                    explanation = "uni (один) + cycle (колесо) = одноколёсный велосипед"
                )
            )
        ),
        Affix(
            name = "-able",
            meaning = "Означает «способность»",
            type = "suffix",
            words = listOf(
                Word(
                    imageResId = R.drawable.binocave,
                    word = "readable",
                    explanation = "read (читать) + able (способный) = читаемый"
                ),
                Word(
                    imageResId = R.drawable.binocave,
                    word = "comfortable",
                    explanation = "comfort (комфорт) + able (способный) = комфортный"
                )
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToMainMenu: Button = findViewById(R.id.btn_to_main_menu)
        buttonToMainMenu.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        val viewPager: ViewPager2 = findViewById(R.id.verticalViewPager)

        val adapter = AffixPagerAdapter(this, affixes)
        viewPager.adapter = adapter



    }





}