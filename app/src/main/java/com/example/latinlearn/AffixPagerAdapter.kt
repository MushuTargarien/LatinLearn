package com.example.latinlearn

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AffixPagerAdapter(
    activity: FragmentActivity,
    private val affixes: List<Affix>
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = affixes.size

    override fun createFragment(position: Int): Fragment {
        return AffixFragment.newInstance(affixes[position])
    }
    fun getItem(position: Int): Affix{
        return affixes[position]
    }
    companion object{
        fun create(activity: FragmentActivity, context: Context): AffixPagerAdapter{
            val repository = AffixRepository(context)
            val affixes = repository.getAllAffixes()
            repository.close()
            return AffixPagerAdapter(activity, affixes)
        }
    }
}