package com.example.retrofitforecaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster.databinding.ListItemWeatherBinding

class WeatherAdapter :
    ListAdapter<ListElement, WeatherAdapter.WeatherViewHolder>(WEATHER_COMPARISON) {

    companion object {
        private val WEATHER_COMPARISON = object : DiffUtil.ItemCallback<ListElement>() {
            override fun areItemsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
                return oldItem.dt == newItem.dt
            }
            override fun areContentsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            ListItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeatherViewHolder(private val binding: ListItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ListElement) {
            binding.tvDate.text = data.dtTxt

            val temp = data.main.temp
            if (temp > 0) {
                binding.tvTempPositive.visibility = View.VISIBLE
                binding.tvTempPositive.text = "$temp °C"
                binding.tvTempNegative.visibility = View.GONE
                binding.imgIcon.setImageResource(R.drawable.warm)
            } else {
                binding.tvTempNegative.visibility = View.VISIBLE
                binding.tvTempNegative.text = "$temp °C"
                binding.tvTempPositive.visibility = View.GONE
                binding.imgIcon.setImageResource(R.drawable.cold)
            }
        }
    }
}