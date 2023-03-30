package com.example.rickandmorty.activities

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Adapter
import com.example.rickandmorty.CharacterItem
import com.example.rickandmorty.CharactersViewModel
import com.example.rickandmorty.R


class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private val model: CharactersViewModel by viewModels()
    private val adapter = Adapter { position ->
        val positionOnCharacter = model.handleClick(position)
        if (positionOnCharacter) {
            startCharacterEpisodeActivity(position)
        }
    }

    private fun startCharacterEpisodeActivity(pos: Int){
        intent = Intent(this, CharacterActivity::class.java)
        val chosenChar = model.getCharacterByPosition(pos) as CharacterItem.CharacterInfo
        val description: ArrayList<String> = arrayListOf()
        description.add(chosenChar.character.image)
        description.add(chosenChar.character.name)
        description.add(chosenChar.character.status)
        description.add(chosenChar.character.type)
        description.add(chosenChar.character.gender)
        description.add(chosenChar.character.species)
        description.add(chosenChar.character.origin.name)
        intent.putExtra("CharacterData", description)
        intent.putExtra("CharacterEpisodes", chosenChar.character.episode as ArrayList<String>)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)

        title = ""
        val recyclerView: RecyclerView = findViewById(R.id.rView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        observe()

        val searchEditText: EditText = findViewById(R.id.search_edit_text)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                model.search(s)
            }
        })
    }

    private fun observe() {
        model.characterList.observe(this) {
            adapter.submitList(it)
        }
    }
}
