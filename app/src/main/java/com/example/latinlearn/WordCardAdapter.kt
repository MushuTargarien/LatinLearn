package com.example.latinlearn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.util.CoilUtils.result

class WordCardAdapter(
    private val examples: List<ExampleModel>,
    private val context: Context,
    private val affixTitle: String,
    private val affixMeaning: String,
) : RecyclerView.Adapter<WordCardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val wordText: TextView = view.findViewById(R.id.textViewWord)
        val explanationText: TextView = view.findViewById(R.id.textViewExplanation)
        val affixTitleText: TextView = view.findViewById(R.id.affixTitle)
        val affixMeaningText: TextView = view.findViewById(R.id.affixMeaning)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_word_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val example = examples[position]
        holder.wordText.text = example.word
        holder.explanationText.text = example.explanation
        holder.affixTitleText.text = affixTitle
        holder.affixMeaningText.text = affixMeaning
        holder.imageView.load(example.image){
            placeholder(android.R.drawable.progress_horizontal)
            error(R.drawable.error_image)
            crossfade(true)
            scale(Scale.FIT)
            listener(
                onSuccess = { _, result ->
                },
                onError = { _, error ->
                    android.util.Log.e("Coil", "Ошибка загрузки: ${error.throwable.message}", error.throwable)
                }
            )
        }

            /*
        try{
            val resourceId = context.resources.getIdentifier(
                example.image.replace(".png",""),
                "drawable",
                context.packageName
            )
            if(resourceId != 0){
                holder.imageView.setImageResource(resourceId)
            } else{
                holder.imageView.setImageResource(R.drawable.bicycle)
            }
        }catch(e: Exception){
            holder.imageView.setImageResource(R.drawable.bicycle)
        }

             */
    }

    override fun getItemCount() = examples.size
}