package com.example.latinlearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class PrefixesFragment : Fragment() {

    // Данные для карточек приставок
    private val prefixPages = listOf(
        AffixPage(
            imageResId = R.drawable.bicycle,
            title = "bi-",
            meaning = "Означает «два»",
            word = "Пример: bicycle",
            explanation = "bi - «два» + cyclus - круг = велосипед"
        ),
        AffixPage(
            imageResId = R.drawable.binocave,
            title = "tri-",
            meaning = "Означает «три»",
            word = "Пример: triangle",
            explanation = "tri - «три» + angle - угол = треугольник"
        ),
        AffixPage(
            imageResId = R.drawable.binocular,
            title = "uni-",
            meaning = "Означает «один»",
            word = "Пример: unicorn",
            explanation = "uni - «один» + cornu - рог = единорог"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_affix, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Находим горизонтальный пейджер
        val horizontalViewPager: ViewPager2 = view.findViewById(R.id.horizontalViewPager)
        val horizontalDots: DotsIndicator = view.findViewById(R.id.horizontalDots)

        // Создаём адаптер для карточек
        val adapter = AffixCardAdapter(prefixPages, requireContext())
        horizontalViewPager.adapter = adapter

        // Привязываем индикаторы
        horizontalDots.attachTo(horizontalViewPager)
    }
}