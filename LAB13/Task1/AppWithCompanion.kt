package com.example.rickandmorty

import android.app.Application

class AppWithCompanion: Application(){
    companion object {
        lateinit var charApi: RetrofitCharServices
        lateinit var epApi: RetrofitEpServices
    }

    override fun onCreate() {
        super.onCreate()
        charApi = Common.retrofitCharService
        epApi = Common.retrofitEpService
    }
}
