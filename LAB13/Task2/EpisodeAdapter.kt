package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EpisodeAdapter : ListAdapter<EpItem, EpisodeViewHolder>(EpisodeItemDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ep_item_rview, parent, false)
        return EpisodeViewHolder(view)
    }
    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val data = currentList[position]
        holder.bindTo(data)
    }
}


class EpisodeItemDiffCallback : DiffUtil.ItemCallback<EpItem>(){
    override fun areItemsTheSame(oldItem: EpItem, newItem: EpItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: EpItem, newItem: EpItem) = oldItem == newItem
}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.episodeNumber)
    val date: TextView = view.findViewById(R.id.episodeDate)

    fun bindTo(ep: EpItem){
        name.text = ep.episode
        date.text = ep.air_date
    }
}
