package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class MainActivity : AppCompatActivity() {

    // Данные для страниц
    private val pages = listOf(
        PageData(
            imageResId = R.drawable.bicycle,
            title = "bi-",
            meaning = "Означает «два»",
            word = "bicycle",
            explanation = "bi - «два» + cyclus - круг = велосипед - трансопрт с 2 кругами-колёсами"
        ),
        PageData(
            imageResId = R.drawable.binocular,
            title = "bi-",
            meaning = "Означает «два»",
            word = "binocular",
            explanation = "bi - «два» + «ocularis »  - глаз = прилагательное, которое означает «относящийся к обоим глазам»"
        ),
        PageData(
            imageResId = R.drawable.binocave,
            title = "bi-",
            meaning = "Означает «два»",
            word = "Biconcave ",
            explanation = "bi - «два» + «concavus» - полый или изогнутый внутрь = форма, которая вогнута с обеих сторон"
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

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = PageAdapter(pages)

        val dotsIndicator: DotsIndicator = findViewById(R.id.dots_indicator)
        dotsIndicator.attachTo(viewPager)

        // Проверяем, пришли ли данные из AffixSelectorActivity
        val hasAffixData = intent.hasExtra("AFFIX_TEXT")

        if (hasAffixData) {
            // Получаем данные
            val affixText = intent.getStringExtra("AFFIX_TEXT") ?: "bi-"
            val affixMeaning = intent.getStringExtra("AFFIX_MEANING") ?: "Означает «два»"
            val affixExample = intent.getStringExtra("AFFIX_EXAMPLE") ?: "bicycle"
            val affixExplanation = intent.getStringExtra("AFFIX_EXPLANATION") ?: "bi - «два» + cyclus - круг = велосипед"

            // Создаем одиночный элемент
            val pages = listOf(
                PageData(
                    imageResId = R.drawable.bicycle,
                    title = affixText,
                    meaning = affixMeaning,
                    word = affixExample,
                    explanation = affixExplanation
                )
            )

            viewPager.adapter = PageAdapter(pages)
            dotsIndicator.attachTo(viewPager)
        } else {
            // Используем исходные данные (50 карточек)
            viewPager.adapter = PageAdapter(pages)
            dotsIndicator.attachTo(viewPager)
        }
    }



    data class PageData(
        val imageResId: Int,
        val title: String,
        val meaning: String,
        val word: String,
        val explanation: String
    )

    // Адаптер для ViewPager2
    class PageAdapter(private val pages: List<PageData>) : RecyclerView.Adapter<PageAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView)
            val titleText: TextView = view.findViewById(R.id.textViewTitle)
            val meaningText: TextView = view.findViewById(R.id.textViewMeaning)
            val wordText: TextView = view.findViewById(R.id.textViewWord)
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
            holder.meaningText.text = page.meaning
            holder.wordText.text = page.word
            holder.explanationText.text = page.explanation
        }

        override fun getItemCount() = pages.size
    }


}