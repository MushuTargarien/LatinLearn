package com.example.latinlearn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordCardAdapter(
    private val words: List<Word>,
    private val context: Context
) : RecyclerView.Adapter<WordCardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val wordText: TextView = view.findViewById(R.id.textViewWord)
        val explanationText: TextView = view.findViewById(R.id.textViewExplanation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_word_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = words[position]
        holder.imageView.setImageResource(word.imageResId)
        holder.wordText.text = word.word
        holder.explanationText.text = word.explanation
    }

    override fun getItemCount() = words.size
}