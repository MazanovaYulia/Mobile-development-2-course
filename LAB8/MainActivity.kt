package com.example.retrofitforecaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mService: RetrofitServices
    val adapter = Adapter()
    var weatherList = listOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        mService = Common.retrofitService
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        getAllWeatherList()
    }

    private fun getAllWeatherList() {
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val data = response.body() as DataWeather
                weatherList = data.list
                adapter.submitList(weatherList)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })
    }
}
