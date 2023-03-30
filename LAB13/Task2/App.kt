package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.retrofitpkg.RetrofitServices

class App: Application() {
    companion object {
        lateinit var apiService: RetrofitServices
            private set
    }

    override fun onCreate() {
        super.onCreate()
        apiService = RetrofitServices.createApiService()
    }
}
