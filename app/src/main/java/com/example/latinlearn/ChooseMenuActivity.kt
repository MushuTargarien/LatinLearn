package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_menu)

        val terms = MathTermRepository.getMathTerms()

        val recyclerView : RecyclerView = findViewById(R.id.termsRecyclerView)

        val adapter = MathTermAdapter(terms, this)

        recyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = layoutManager
    }
}