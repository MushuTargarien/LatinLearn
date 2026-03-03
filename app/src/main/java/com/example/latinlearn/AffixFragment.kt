package com.example.latinlearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class AffixFragment : Fragment() {

    private lateinit var affix: Affix


    companion object {
        fun newInstance(affix: Affix): AffixFragment {
            val fragment = AffixFragment()
            fragment.affix = affix
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_affix, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.horizontalViewPager)
        val horizontalDots: DotsIndicator = view.findViewById(R.id.horizontalDots)

        val adapter = WordCardAdapter(affix.examples, requireContext(), affix.affix, affix.meaning)
        viewPager.adapter = adapter
        horizontalDots.attachTo(viewPager)
    }
}