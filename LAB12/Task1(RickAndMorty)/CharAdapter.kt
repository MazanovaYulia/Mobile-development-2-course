package com.example.rickandmorty


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}

class CharViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    private val context: Context = view.context
    private val name: TextView = view.findViewById(R.id.char_name)
    private val species: TextView = view.findViewById(R.id.char_species)
    private val origin: TextView = view.findViewById(R.id.char_origin)
    private val status: ImageView = view.findViewById(R.id.status_icon)
    private val icon = view.findViewById<ImageView>(R.id.char_icon).apply {
        clipToOutline = true
    }

    init { itemView.setOnClickListener { onItemClicked(adapterPosition) } }

    fun bindTo(character: Character) {
        name.text = context.getString(R.string.name, character.name)
        species.text = context.getString(R.string.species, character.species, character.gender)
        origin.text = context.getString(R.string.origin, character.origin.name)
        status.setBackgroundColor(getStatus(character.status))
        Glide.with(context).load(character.image).into(icon)
    }

    private fun getStatus(status: String): Int {
        return when (status.lowercase(Locale.getDefault())) {
            "alive" ->  Color.HSVToColor(floatArrayOf(120F, 100F, 60F))
            "dead" ->  Color.HSVToColor(floatArrayOf(8F, 100F, 100F))
            else -> Color.HSVToColor(floatArrayOf(0F, 0F, 0.5F))
        }
    }
}

class CharAdapter(private val clickListener: (Character) -> Unit) :
    ListAdapter<Character, RecyclerView.ViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.char_rv_item, parent, false)
        return CharViewHolder(view) {
            clickListener(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val charViewHolder: CharViewHolder = holder as CharViewHolder
        charViewHolder.bindTo(currentList[position])
    }
}
