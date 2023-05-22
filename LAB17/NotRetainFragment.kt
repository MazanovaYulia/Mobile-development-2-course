package com.example.retrofitforecaster

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar

class NotRetainFragment : Fragment( ) {
    private val API_KEY = "a1bdf2e4609febbedaf0fcc823e3d527"
    lateinit var items: Temperatures

    suspend fun getWeatherResponse(lat: String,lon: String): Temperatures {

        Log.e("TESSSST", lon)
        Log.e("TESSSST", lat)
        if (lon == "")
        {
            items = forecastApiService.getHourlyForecastForNextDays("0", "0", API_KEY)
        }
            items = forecastApiService.getHourlyForecastForNextDays(lat, lon, API_KEY)
            Log.e("", "items = $items")
            return items
    }


    fun restoreSavedWeather(): Temperatures {
        return items
    }
}