package com.example.latinlearn

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load



class TermDetailAdapter (
    private val analogies: List<TermWithAnalogy>,
    private val context: Context
): RecyclerView.Adapter<TermDetailAdapter.ViewHolder>() {

     class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
     //   val termText: TextView = view.findViewById(R.id.termText)
     //   val definitionText: TextView = view.findViewById(R.id.definitionText)
        val analogyWordText: TextView = view.findViewById(R.id.analogyWordText)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val analogyDefinitionText: TextView = view.findViewById(R.id.analogyDefinitionText)
        val morphemeFormText: TextView = view.findViewById(R.id.morphemeFormText)
        val morphemeMeaningText: TextView = view.findViewById(R.id.morphemeMeaningText)
        val morphemeExplanation: TextView = view.findViewById(R.id.morphemeExplanationText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_term_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val analogy = analogies[position]
        val analogyWithTranslate = "${analogy.analogyWord} - ${analogy.analogyTranslate}"

        holder.analogyWordText.text = analogyWithTranslate
        holder.morphemeFormText.text = analogy.morphemeForm
        holder.morphemeMeaningText.text = "означает ${analogy.morphemeMeaning}"
        holder.analogyDefinitionText.text = analogy.analogyDefinition
        holder.morphemeExplanation.text = analogy.morphemeExplanation


        holder.imageView.load(analogy.imageUrl) {
            placeholder(R.drawable.load)
            error(R.drawable.error_image)
            crossfade(true)
        }
    }
    override fun getItemCount() = analogies.size
}