package com.example.rickandmorty

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.net.URL

class Adapter(private val cellClickListener: (Int) -> Unit) :
    ListAdapter<CharacterItem, RecyclerView.ViewHolder>(CharacterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewTemplate = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.r_view -> {
                val charViewHolder = CharacterViewHolder(viewTemplate)
                charViewHolder.itemView.setOnClickListener { cellClickListener(charViewHolder.adapterPosition) }
                charViewHolder
            }
            R.layout.load_more_button -> {
                val loadViewHolder = LoadButtonViewHolder(viewTemplate)
                loadViewHolder.itemView.setOnClickListener { cellClickListener(loadViewHolder.adapterPosition) }
                loadViewHolder
            }
            else -> {
                throw Throwable("Err")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            holder.bindCharacter(getItem(position) as CharacterItem.CharacterInfo)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is CharacterItem.NextPage) R.layout.load_more_button
        else R.layout.r_view
    }
}

class LoadButtonViewHolder(view: View) : RecyclerView.ViewHolder(view)

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val avatar: ImageView = view.findViewById(R.id.characterImage)
    private val name: TextView = view.findViewById(R.id.nameAC)

    fun bindCharacter(item: CharacterItem.CharacterInfo) {
        runBlocking {
            Glide
                .with(itemView)
                .load(item.character.image)
                .into(avatar)
            name.text = item.character.name
        }
    }
}

class CharacterItemDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {
    override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem) = oldItem == newItem
}
