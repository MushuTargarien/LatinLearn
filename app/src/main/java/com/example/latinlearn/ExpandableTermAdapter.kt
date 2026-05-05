package com.example.latinlearn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notify
import kotlin.math.PI

class ExpandableTermAdapter (
    private var terms: MutableList<MathTermWithAnalogies>,
    private val onDetailsClick: (Int) -> Unit
): RecyclerView.Adapter<ExpandableTermAdapter.TermViewHolder>() {


    private val expandedPositions = mutableSetOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_term, parent, false)
        return TermViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        val term = terms[position]

        holder.termText.text = "${term.term} - ${term.translate}"

        holder.expandIcon.setImageResource(
            if (expandedPositions.contains(position))
                android.R.drawable.arrow_up_float
            else
                android.R.drawable.arrow_down_float
        )

        holder.expandIcon.setOnClickListener {
            toggleExpansion(position)
        }

        holder.termText.setOnClickListener {
            toggleExpansion(position)
        }

        holder.detailsButton.setOnClickListener {
            onDetailsClick(term.termId)
        }

        if (expandedPositions.contains(position)) {
            holder.analogiesContainer.visibility = View.VISIBLE
            holder.analogiesContainer.removeAllViews()

            for (analogy in term.analogies) {
                val analogyView = LayoutInflater.from(holder.itemView.context)
                    .inflate(R.layout.item_analogy, holder.analogiesContainer, false)
                val analogyText = analogyView.findViewById<TextView>(R.id.analogyText)
                analogyText.text = "• ${analogy.word} - ${analogy.translate}"
                holder.analogiesContainer.addView(analogyView)
            }
        } else {
            holder.analogiesContainer.visibility = View.GONE
            holder.analogiesContainer.removeAllViews()
        }

    }

    override fun getItemCount() = terms.size

    private fun toggleExpansion(termIndex: Int) {
        if (expandedPositions.contains(termIndex)) {
            expandedPositions.remove(termIndex)
        } else {
            expandedPositions.add(termIndex)
        }
        notifyDataSetChanged()
    }

    inner class TermViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val termText: TextView = view.findViewById(R.id.termText)
        val expandIcon: ImageView = view.findViewById(R.id.expandIcon)
        val detailsButton: Button = view.findViewById(R.id.detailsButton)
        val analogiesContainer: LinearLayout = view.findViewById(R.id.analogiesContainer)
    }


    fun updateData(newTerms: List<MathTermWithAnalogies>) {
        expandedPositions.clear()
        terms = newTerms.toMutableList()
        notifyDataSetChanged()
    }
}
