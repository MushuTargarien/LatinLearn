package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var counterTextView: TextView
    private lateinit var repository: AffixRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToMainMenu: Button = findViewById(R.id.btn_to_main_menu)
        buttonToMainMenu.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        val viewPager: ViewPager2 = findViewById(R.id.verticalViewPager)
        counterTextView = findViewById(R.id.TVnumberOfExample)


        repository = AffixRepository(this)
        val adapter = AffixPagerAdapter.create(this,this)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
               updateCounterText(position, adapter.itemCount)
            }
        })
    }

     fun updateCounterText(position: Int, total: Int){
         counterTextView.text = "${position+ 1 }/$total"
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}