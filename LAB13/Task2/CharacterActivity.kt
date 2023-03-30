package com.example.rickandmorty.activities

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

class CharacterActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private val adapter by lazy { EpisodeAdapter() }
    private val model: EpisodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val description = intent.getStringArrayListExtra("CharacterData")
        val episodes = intent.getStringArrayListExtra("CharacterEpisodes")

        if (model.episodeList.value == null) {
            model.getInfo(episodes as ArrayList<String>)
        }

        val recyclerView: RecyclerView = findViewById(R.id.ep_rView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        title = description?.get(1)

        val statusIcon: ImageView = findViewById(R.id.statusIcon)
        val avatarImage: ImageView = findViewById(R.id.avatarAC)
        val name: TextView = findViewById(R.id.nameAC)
        val statusSpecies: TextView = findViewById(R.id.statusSpeciesText)
        val gender: TextView = findViewById(R.id.genderText)
        val planet: TextView = findViewById(R.id.planetText)

        val imageInputStream: InputStream = URL(description?.get(0)).openStream()
        val image = BitmapFactory.decodeStream(imageInputStream)
        avatarImage.setImageBitmap(image)

        when (description?.get(2)) {
            "Alive" -> statusIcon.setImageResource(R.mipmap.ic_alive)
            "Dead" -> statusIcon.setImageResource(R.mipmap.ic_death)
            "unknown" -> statusIcon.setImageResource(R.mipmap.ic_unknown)
        }

        name.text = description?.get(1)
        statusSpecies.text = "${description?.get(2)} - ${description?.get(5)}"
        gender.text = description?.get(4)
        planet.text = description?.get(6)

        model.episodeList.observe(this) {
            adapter.submitList(it)
        }
    }


}
