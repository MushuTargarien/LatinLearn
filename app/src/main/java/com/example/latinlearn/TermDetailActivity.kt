package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class TermDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_term_detail)

        val analogies = TermWithAnalogyRepository.getAnalogiesForTerm(termId = 1)


        val cardsViewPager : ViewPager2 = findViewById(R.id.cardsViewPager)
        val dotsIndicator : DotsIndicator = findViewById(R.id.dotsIndicator)

        val adapter = TermDetailAdapter(analogies,this)
        cardsViewPager.adapter = adapter
        dotsIndicator.attachTo(cardsViewPager)

        val btnToMainMenu: Button = findViewById(R.id.btn_from_term_actvt_to_mainmenu)
        btnToMainMenu.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }

}

