package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val cellClickListener: (Character) -> Unit = { data ->
        onCellClickListener(data)
    }
    private var charAdapter = CharAdapter(cellClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: CharPageViewModel by viewModels()
        model.getCharData().observe(this) { charData ->
            charAdapter.submitList(charData)
        }

        findViewById<RecyclerView>(R.id.char_recycler).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = charAdapter
        }
    }

    private fun onCellClickListener(data: Character) {
        intent = Intent(this, CharacterPage::class.java)
        intent.putExtra("charData", data)
        startActivity(intent)
    }
}
