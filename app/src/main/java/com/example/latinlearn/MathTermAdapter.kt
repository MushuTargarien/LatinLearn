package com.example.latinlearn


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MathTermAdapter(
    private val terms: List<MathTerm>,
    private val onTermClick: (MathTerm) ->Unit
) : RecyclerView.Adapter<MathTermAdapter.ViewHolder>() {

     class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val termBtn: Button = view.findViewById(R.id.termText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_math_term, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val term = terms[position]

        holder.termBtn.text = term.term

        holder.termBtn.setOnClickListener {
            onTermClick(term)
        }
    }

    override fun getItemCount() = terms.size
}