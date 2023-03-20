package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val cellClickListener: (RecyclerItem.CharacterItem) -> Unit = { data ->
        onCellClickListener(data)
    }
    private val buttonClickListener: (RecyclerItem.ButtonItem) -> Unit = { data ->
        onButtonClickListener(data)
    }
    private var charAdapter = CharAdapter(cellClickListener, buttonClickListener)
    private var model: CharPageViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = CharPageViewModel()
        model!!.getCharData().observe(this) { charData ->
            charAdapter.submitList(charData)
        }

        findViewById<RecyclerView>(R.id.char_recycler).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = charAdapter
        }
    }

    private fun onCellClickListener(data: RecyclerItem.CharacterItem) {
        intent = Intent(this, CharacterPage::class.java)
        intent.putExtra("charData", data)
        startActivity(intent)
    }

    private fun onButtonClickListener(data: RecyclerItem.ButtonItem) {
        model!!.uploadPage(data.next)
    }
}
