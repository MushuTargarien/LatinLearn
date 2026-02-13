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

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)




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


    }

}