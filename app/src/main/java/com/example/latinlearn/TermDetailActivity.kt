package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class TermDetailActivity : AppCompatActivity() {

    private lateinit var repository : TermWithAnalogyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_detail)

        val termId = intent.getIntExtra("TERM_ID",1)

        val analogyMode = AnalogyMode.valueOf(
            intent.getStringExtra("ANALOGY_MODE") ?: AnalogyMode.LIFE.name
        )
        repository = TermWithAnalogyRepository(this)
        val analogies = repository.getAnalogiesForTerm(termId, analogyMode)

        val cardsViewPager : ViewPager2 = findViewById(R.id.cardsViewPager)
        val dotsIndicator : DotsIndicator = findViewById(R.id.dotsIndicator)

        val adapter = TermDetailAdapter(analogies,this)
        cardsViewPager.adapter = adapter
        dotsIndicator.attachTo(cardsViewPager)

        if(analogies.isNotEmpty()){
            findViewById<TextView>(R.id.termText).text = getString(
                R.string.term_title,
                analogies[0].term, analogies[0].termTranslate
            )
            findViewById<TextView>(R.id.definitionText).text = analogies[0].definition
        }

        val btnToMainMenu: Button = findViewById(R.id.btn_from_term_actvt_to_mainmenu)
        btnToMainMenu.setOnClickListener {
            val intent = Intent(this, ChooseMenuActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        repository.close()
    }
}

