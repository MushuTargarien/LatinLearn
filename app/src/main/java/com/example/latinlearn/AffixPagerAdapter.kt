package com.example.latinlearn

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
}