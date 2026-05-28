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

    private val expandedTermId = mutableSetOf<Int>()
    private val expandedPositions = mutableSetOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_term, parent, false)
        return TermViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        val term = terms[position]
        val isExpanded = expandedTermId.contains(term.termId)

        holder.termText.text = "${term.term} - ${term.translate}"

        holder.expandIcon.setImageResource(
            if (isExpanded) android.R.drawable.arrow_up_float
            else android.R.drawable.arrow_down_float
        )

        holder.expandIcon.setOnClickListener {
            toggleExpansion(term.termId)
        }

        holder.termText.setOnClickListener {
            toggleExpansion(term.termId)
        }

        holder.detailsButton.setOnClickListener {
            onDetailsClick(term.termId)
        }

        holder.analogiesContainer.removeAllViews()

        if (isExpanded) {
            holder.analogiesContainer.visibility = View.VISIBLE

            if (term.analogies.isEmpty()) {
                val emptyText = TextView(holder.itemView.context)
                emptyText.text = "Нет аналогий для этого режима"
                holder.analogiesContainer.addView(emptyText)
            } else {
                for (analogy in term.analogies) {
                    val analogyView = LayoutInflater.from(holder.itemView.context)
                        .inflate(R.layout.item_analogy, holder.analogiesContainer, false)
                    val analogyText = analogyView.findViewById<TextView>(R.id.analogyText)
                    analogyText.text = "• ${analogy.word} - ${analogy.translate}"
                    holder.analogiesContainer.addView(analogyView)
                }
            }
        } else {
            holder.analogiesContainer.visibility = View.GONE
        }
    }


    override fun getItemCount() = terms.size

    private fun toggleExpansion(termIndex: Int) {
        if (expandedTermId.contains(termIndex)) {
            expandedTermId.remove(termIndex)
        } else {
            expandedTermId.add(termIndex)
        }
        notifyDataSetChanged()
    }


    fun updateData(newTerms: List<MathTermWithAnalogies>) {
        terms = newTerms.toMutableList()
        notifyDataSetChanged()
    }

    inner class TermViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val termText: TextView = view.findViewById(R.id.termText)
        val expandIcon: ImageView = view.findViewById(R.id.expandIcon)
        val detailsButton: Button = view.findViewById(R.id.detailsButton)
        val analogiesContainer: LinearLayout = view.findViewById(R.id.analogiesContainer)
    }
}
