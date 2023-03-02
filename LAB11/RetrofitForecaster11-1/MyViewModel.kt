package com.example.retrofitforecaster

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    private var adapter: Adapter? = null
    fun getAdapter(): Adapter? {
        if (adapter == null) {
            adapter = Adapter()
            loadAdapter()
        }
        return adapter
    }

    private fun loadAdapter(){
        val mService = Common.retrofitService
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val data = response.body() as DataWeather
                adapter?.submitList(data.list)
            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })
    }
}
