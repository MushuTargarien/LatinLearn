package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.temporal.TemporalQuery

class ChooseMenuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ExpandableTermAdapter
    private lateinit var repository : MathTermRepository
    private var allTerms: List<MathTermWithAnalogies> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_menu)

        recyclerView = findViewById(R.id.termsRecyclerView)
        searchView  = findViewById(R.id.searchView)

        repository = MathTermRepository(this)
        allTerms = repository.getAllTermWithAnalogies()



        adapter = ExpandableTermAdapter(allTerms.toMutableList()) { termId ->
            val intent = Intent(this@ChooseMenuActivity, TermDetailActivity::class.java)
            intent.putExtra("TERM_ID", termId)
            startActivity(intent)
            finish()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterTerms(newText ?: "")
                return true
            }
        })
    }

    private fun filterTerms(query: String) {
        val filteredTerms = if (query.isEmpty()) {
            allTerms
        } else {
            allTerms.filter {
                it.term.contains(query, ignoreCase = true)    ||
                it.translate.contains(query, ignoreCase = true)
            }
        }
        adapter.updateData(filteredTerms)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.close()
    }
}