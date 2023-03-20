package com.example.rickandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Episode, newItem: Episode) = oldItem == newItem
}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val context: Context = view.context
    private val name: TextView = view.findViewById(R.id.episode_name)
    private val date: TextView = view.findViewById(R.id.air_date)

    fun bindTo(episode: Episode) {
        name.text = context.getString(R.string.episode_name, episode.episode, episode.name)
        date.text = context.getString(R.string.air_date, episode.air_date)
    }
}

class EpisodeAdapter : ListAdapter<Episode, RecyclerView.ViewHolder>(EpisodeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_rv_item, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episodeViewHolder: EpisodeViewHolder = holder as EpisodeViewHolder
        episodeViewHolder.bindTo(currentList[position])
    }
}
