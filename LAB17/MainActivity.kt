package com.example.retrofitforecaster

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.*
import com.example.retrofitforecaster.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
private lateinit var fusedLocationClient: FusedLocationProviderClient
private var starting = true
private var coordX = ""
private var coordY = ""


    private lateinit var notRetainFragment: NotRetainFragment
    lateinit var items: Temperatures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val parentLayout: View = findViewById(R.id.content)

        val snack = Snackbar.make(parentLayout,"Не хватает прав доступа",Snackbar.LENGTH_LONG)
        val adapter = WeatherAdapter()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cordDao"
        ).allowMainThreadQueries().build()

        val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            permissions.entries.forEach {
                Log.e("DEBUG", "${it.key} = ${it.value}")
                starting = it.value
                Log.e("DEBUG", starting.toString())
                if (!starting)
                {
                    snack.show()
                }
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.e("GPS", fusedLocationClient.toString())

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
        {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )

        }

        if (savedInstanceState == null) {
            notRetainFragment = NotRetainFragment().apply {
                supportFragmentManager.beginTransaction()
                    .add(NotRetainFragment::class.java, savedInstanceState, "retainFragment")
                    .commit()

            }
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        if (location != null) {
                            Log.e("LOCATION", location.latitude.toString())
                            Log.e("LOCATION", location.longitude.toString())
                            var lat = location.latitude.toString()
                            var  lon = location.longitude.toString()

                            val corddao = db.cordDao()

                            coordX = corddao.getX()
                            coordY = corddao.getY()
                            val cordGet: List<Coordinate> = corddao.getAll()
                           if(coordX == "" || coordX != lat || coordY == "" || coordY != lon)
                            {
                                val newNote = Coordinate(lat, lon)
                                GlobalScope.launch {
                                    corddao.update(cord = newNote)
                                }
                            }
                            if(cordGet.isEmpty())
                            {
                                val newNote = Coordinate(lat, lon)
                                GlobalScope.launch {
                                    corddao.insertAll(cord = newNote)
                                }
                            }

                            coordX = corddao.getX()
                            coordY = corddao.getY()


                            Log.e("BASETEST", cordGet.toString())


                        }
                    }

                delay(3000)
                if (starting == true && coordX != "") {
                    items = notRetainFragment.getWeatherResponse(coordX, coordY)
                    withContext(Dispatchers.Main) {
                        adapter.submitList(items.list)
                    }
                }
            }

        } else {
            notRetainFragment = (supportFragmentManager.getFragment(
                savedInstanceState, "retainFragment"
            ) as NotRetainFragment)
            items = notRetainFragment.restoreSavedWeather()
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    adapter.submitList(items.list)
                }
            }

        }
        with(binding.rView) {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL)
            )
        }
    }
}

