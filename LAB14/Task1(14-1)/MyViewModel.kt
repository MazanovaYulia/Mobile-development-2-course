package com.example.retrofitforecaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitforecaster.AppWithCompanion.Companion.api
import com.example.retrofitforecaster.AppWithCompanion.Companion.db
import kotlinx.coroutines.*
import okhttp3.Dispatcher

@DelicateCoroutinesApi
class MyViewModel : ViewModel() {
    private val weatherData: MutableLiveData<List<Weather>> by lazy {
        MutableLiveData<List<Weather>>().also {
            runBlocking { loadWeatherData() }
        }
    }

    fun getWeatherData(): LiveData<List<Weather>> {
        return weatherData
    }

    private suspend fun loadWeatherData() = GlobalScope.launch(Dispatchers.IO) {
        val result = api.getWeatherList().execute()
        if (!result.isSuccessful) {
            weatherData.postValue(readDB())
            return@launch
        }
        val fetchedList = result.body()!!.toWeatherList()

        if (!isActual(fetchedList, readDB())) {
            writeDB(result.body() as DataWeather)
        }
        weatherData.postValue(fetchedList)
    }

    private fun DataWeather.toWeatherList(): List<Weather> {
        var list = listOf<Weather>()
        this.list.forEach { weatherObject ->
            list = list + Weather(weatherObject.main.temp, weatherObject.dt_txt, weatherObject.weather?.get(0)!!.description)
        }
        return list
    }

    private fun isActual(firstList: List<Weather>, secondList: List<Weather>): Boolean {
        return secondList[0].dt_txt == firstList[0].dt_txt
    }

    private suspend fun readDB() = coroutineScope {
        db.weatherDao().getAll()
    }

    private suspend fun writeDB(dataWeather: DataWeather) = coroutineScope {
        db.weatherDao().deleteAll()
        dataWeather.list.forEach { weatherObject ->
            db.weatherDao().insertAll(Weather(weatherObject.main.temp, weatherObject.dt_txt, weatherObject.weather?.get(0)!!.description))
        }
    }
}
