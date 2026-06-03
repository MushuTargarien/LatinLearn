package com.example.latinlearn

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Locale
import androidx.recyclerview.widget.RecyclerView


class ExpandableTermAdapter (
    private var terms: MutableList<MathTermWithAnalogies>,
    private val onDetailsClick: (Int) -> Unit
): RecyclerView.Adapter<ExpandableTermAdapter.TermViewHolder>() {

    private val expandedTermId = mutableSetOf<Int>()


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
            if (isExpanded) R.drawable.up
            else R.drawable.down
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
                    analogyText.text = makeAnalogyText(analogy)
                    holder.analogiesContainer.addView(analogyView)
                }
            }
        } else {
            holder.analogiesContainer.visibility = View.GONE
        }


    }

    private fun makeAnalogyText(analogy: AnalogyItem): SpannableString {
        val text = "• ${analogy.word} - ${analogy.translate}"
        val spannable = SpannableString(text)

        val morphemeForms = analogy.morphemeForm.split("/").map { it.replace("-", "").trim()}.filter { it.isNotEmpty() }.sortedByDescending { it.length }


        val wordStartIndex = text.indexOf(analogy.word)
        if ( wordStartIndex == -1){ return  spannable}

        val lowerWord = analogy.word.lowercase(Locale.ROOT)

        for (form in morphemeForms) {
            val morphemeIndexInWord = lowerWord.indexOf(form.lowercase(Locale.ROOT))
            if (wordStartIndex != -1 && morphemeIndexInWord != -1){
                val start = wordStartIndex + morphemeIndexInWord
                val end = start + form.length

                spannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                break
            }
        }
        return  spannable
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
