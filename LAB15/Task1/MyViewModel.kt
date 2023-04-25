package com.example.retrofitforecaster_lab10

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val weatherData: MutableLiveData<List<ListItem>> by lazy {
        MutableLiveData<List<ListItem>>().also {
            loadWeatherData()
        }
    }

    fun getWeatherData(): LiveData<List<ListItem>> {
        return weatherData
    }

    private fun loadWeatherData() {
        val mService = Common.retrofitService
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val dataWeather = response.body() as DataWeather
                weatherData.value = dataWeather.list
            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })
    }
}