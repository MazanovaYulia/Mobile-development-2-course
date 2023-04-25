package com.example.retrofitforecaster_lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    private val longClickListener: (ListItem) -> Unit = { item ->
        onLongClick(item)
    }
    private val adapter = Adapter(longClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        val model: MyViewModel by viewModels()
        model.getWeatherData().observe(this) { weatherData ->
            adapter.submitList(weatherData)
        }

        mService = Common.retrofitService
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun onLongClick(listItem: ListItem) {
        val weather: String =
            "City: ${getString(R.string.City)}, " +
                    "Date: ${listItem.dt_txt} \n" +
                    "Temperature: ${listItem.main.temp}"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, weather)
            type = "text/*"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }
}
