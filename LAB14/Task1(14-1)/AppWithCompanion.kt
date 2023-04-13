package com.example.retrofitforecaster

import android.app.Application
import androidx.room.Room

class AppWithCompanion : Application() {
    companion object {
        lateinit var db: DB
        lateinit var api: RetrofitServices
    }

    override fun onCreate() {
        super.onCreate()

        api = Common.retrofitService
        db = Room.databaseBuilder(applicationContext, DB::class.java, "db").build()
    }
}
