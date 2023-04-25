package com.example.retrofitforecaster_lab10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
}

class WeatherViewHolder(view: View, private val longClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    private val temperature: TextView = view.findViewById(R.id.temperature_text)
    private val datetime: TextView = view.findViewById(R.id.datetime_text)
    private val description: TextView = view.findViewById(R.id.desc_text)
    init {
        view.setOnLongClickListener {
            longClick(adapterPosition)
            return@setOnLongClickListener true
        }
    }

    fun bindTo(weather: ListItem) {
        temperature.text = weather.main.temp.toString()
        datetime.text = weather.dt_txt
        description.text = weather.weather?.get(0)?.description
    }
}

class Adapter(private val longClickListener: (ListItem) -> Unit) : ListAdapter<ListItem, RecyclerView.ViewHolder>(
    ContactItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rview_item, parent, false)
        view.setBackgroundResource(viewType)
        return WeatherViewHolder(view) {
            longClickListener (currentList[it])
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        val viewHolderHot: WeatherViewHolder = holder as WeatherViewHolder
        viewHolderHot.bindTo(data)
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].main.temp > 0) {
            R.drawable.hot
        }
        else {
            R.drawable.cold
        }
    }
}