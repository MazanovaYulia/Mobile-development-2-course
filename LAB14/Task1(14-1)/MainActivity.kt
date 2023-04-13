package com.example.retrofitforecaster

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import timber.log.Timber

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        val model: MyViewModel by viewModels()
        model.getWeatherData().observe(this) { weatherData ->
            adapter.submitList(weatherData)
            Timber.plant(Timber.DebugTree())
            Timber.d(weatherData.toString())
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
