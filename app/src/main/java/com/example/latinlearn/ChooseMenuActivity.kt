package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseMenuActivity : AppCompatActivity() {

    private lateinit var repository : TermWithAnalogyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_menu)

        repository = TermWithAnalogyRepository(this)

        val terms = repository.getAllMathTerms()

        val recyclerView : RecyclerView = findViewById(R.id.termsRecyclerView)

        val adapter = MathTermAdapter(terms) { term ->
            val intent = Intent(this@ChooseMenuActivity, TermDetailActivity::class.java)
            intent.putExtra("TERM_ID",term.id)
            startActivity(intent)
            finish()
        }
        recyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = layoutManager
    }

}