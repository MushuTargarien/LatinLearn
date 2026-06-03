package com.example.latinlearn

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.temporal.TemporalQuery

class ChooseMenuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var analogyModeSwitch: SwitchCompat
    private lateinit var adapter: ExpandableTermAdapter
    private lateinit var repository : MathTermRepository
    private var allTerms: List<MathTermWithAnalogies> = emptyList()
    private var analogyMode: AnalogyMode = AnalogyMode.LIFE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)

        recyclerView = findViewById(R.id.termsRecyclerView)
        searchView  = findViewById(R.id.searchView)
        analogyModeSwitch = findViewById(R.id.analogyModeSwitch)

        repository = MathTermRepository(this)
        allTerms = repository.getAllTermWithAnalogies(analogyMode)



        adapter = ExpandableTermAdapter(allTerms.toMutableList()) { termId ->
            val intent = Intent(this@ChooseMenuActivity, TermDetailActivity::class.java)
            intent.putExtra("TERM_ID", termId)
            intent.putExtra("ANALOGY_MODE", analogyMode.name)
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

        analogyModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            analogyMode = if(isChecked) AnalogyMode.MATH else AnalogyMode.LIFE
            allTerms = repository.getAllTermWithAnalogies(analogyMode)
            filterTerms(searchView.query.toString())
        }
    }

    private fun filterTerms(query: String) {
       val normalizedQuery = query.trim()

        val filteredTerms = if ( normalizedQuery.isEmpty()){
            allTerms
        } else {
            allTerms.filter {
                it.term.startsWith(normalizedQuery, ignoreCase = true) ||
                        it.translate.startsWith(normalizedQuery, ignoreCase = true)
            }
        }
        adapter.updateData(filteredTerms)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.close()
    }
}