package com.example.latinlearn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class AffixAdapter(
    private val affixes: List<Affix>,
    private val onItemClick: (Affix) -> Unit
) : RecyclerView.Adapter<AffixAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.buttonAffix)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_affix_button, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val affix = affixes[position]
        holder.button.text = affix.text
        holder.button.setOnClickListener { onItemClick(affix) }
    }

    override fun getItemCount() = affixes.size
}