package com.example.latinlearn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AffixCardAdapter(
    private val pages: List<AffixPage>,
    private val context: Context
) : RecyclerView.Adapter<AffixCardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleText: TextView = view.findViewById(R.id.textViewTitle)
        val meaningText: TextView = view.findViewById(R.id.textViewMeaning)
        val wordText: TextView = view.findViewById(R.id.textViewWord)
        val explanationText: TextView = view.findViewById(R.id.textViewExplanation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_word_card, parent, false)
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