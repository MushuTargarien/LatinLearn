package com.example.latinlearn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class MainActivity : AppCompatActivity() {

    // Данные для страниц
    private val pages = listOf(
        PageData(
            imageResId = android.R.drawable.ic_menu_gallery,
            title = "bi-",
            explanation = "Означает «два».\nПримеры: bicycle, biennial"
        ),
        PageData(
            imageResId = android.R.drawable.ic_menu_camera,
            title = "tri-",
            explanation = "Означает «три».\nПримеры: triangle, tricycle"
        ),
        PageData(
            imageResId = android.R.drawable.ic_media_ff,
            title = "asd-",
            explanation = "Означает «asd».\nПримеры: asd, asddsa"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим ViewPager2 по ID
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        // Устанавливаем адаптер
        viewPager.adapter = PageAdapter(pages)

        val dotsIndicator: DotsIndicator = findViewById(R.id.dots_indicator)
        dotsIndicator.attachTo(viewPager)
    }



    // Модель данных
    data class PageData(
        val imageResId: Int,
        val title: String,
        val explanation: String
    )

    // Адаптер для ViewPager2
    class PageAdapter(private val pages: List<PageData>) :
        RecyclerView.Adapter<PageAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView)
            val titleText: TextView = view.findViewById(R.id.textViewTitle)
            val explanationText: TextView = view.findViewById(R.id.textViewExplanation)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_page, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val page = pages[position]
            holder.imageView.setImageResource(page.imageResId)
            holder.titleText.text = page.title
            holder.explanationText.text = page.explanation
        }

        override fun getItemCount() = pages.size
    }


}