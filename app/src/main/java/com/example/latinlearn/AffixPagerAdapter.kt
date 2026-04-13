package com.example.latinlearn

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
/*
*   "Мост" между данными из БД и визуальным отображением
*    загружает данные из БД при создании
*   создаёт фрагменты по мере листания
*   Оптимизирует память при помощи FragmentStateAdapter (создаёт\уничтожает элементы при листании)
*/
class AffixPagerAdapter(
    activity: FragmentActivity,
    private val affixes: List<Affix>
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = affixes.size

    override fun createFragment(position: Int): Fragment {
        return AffixFragment.newInstance(affixes[position])
    }
    /*
    companion object{
        fun create(activity: FragmentActivity, context: Context): AffixPagerAdapter{
            val repository = AffixRepository(context)
            val affixes = repository.getAllAffixes()
            repository.close()
            return AffixPagerAdapter(activity, affixes)
        }
    }

     */
}