package com.example.rickandmorty

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class CharacterPage : AppCompatActivity() {
    private var episodeAdapter = EpisodeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_page)

        val characterData = intent
            .getSerializableExtra("charData") as RecyclerItem.CharacterItem
        displayCharacterData(characterData)
        val episodes = EpisodeParser(characterData)
        episodeAdapter.submitList(episodes.listOfEpisodes)

        findViewById<RecyclerView>(R.id.episodes_recycler).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = episodeAdapter
        }
    }

    private fun displayCharacterData(character: RecyclerItem.CharacterItem) {

        fun getStatus(status: String): Int {
            return when (status.lowercase(Locale.getDefault())) {
                "alive" ->  Color.HSVToColor(floatArrayOf(120F, 100F, 60F))
                "dead" ->  Color.HSVToColor(floatArrayOf(8F, 100F, 100F))
                else -> Color.HSVToColor(floatArrayOf(0F, 0F, 0.5F))
            }
        }

        fun bindData(view: View?, character: RecyclerItem.CharacterItem) {
            view?.findViewById<TextView?>(R.id.char_name)?.apply {
                text = getString(R.string.name, character.name)
            }
            view?.findViewById<TextView?>(R.id.char_species)?.apply {
                text = getString(R.string.species, character.species, character.gender)
            }
            view?.findViewById<TextView?>(R.id.char_origin)?.apply {
                text = getString(R.string.origin, character.origin.name)
            }
            view?.findViewById<ImageView>(R.id.char_icon)?.apply {
                clipToOutline = true
                Glide.with(this.context).load(character.image).into(this)
            }
            view?.findViewById<ImageView?>(R.id.status_icon)?.apply {
                setBackgroundColor(getStatus(character.status))
            }
        }

        fun createView(): View? {
            val viewCont = findViewById<RelativeLayout>(R.id.char_rv_item)
            val view = LayoutInflater.from(this)
                .inflate(R.layout.char_rv_item, null).also {
                bindData(it, character)
            }
            viewCont.addView(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            return view
        }

        createView()
    }
}
