package com.example.internettest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHTTP = findViewById<Button>(R.id.btnHTTP)
        btnHTTP.setOnClickListener {
            val thread = Thread {
                var urlConnection: HttpURLConnection? = null
                val reader: BufferedReader? = null
                try {
                    urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connect()
                    val inputStream = urlConnection.inputStream
                    val a = inputStream.bufferedReader().use {it.readText() }
                    Log.v("Flickr cats", a)
                } catch (e: IOException) {
                    Log.e("Request", "Error ", e)
                    return@Thread
                } finally {

                    urlConnection?.disconnect()
                    if (reader != null) {
                        try {
                            reader.close()
                        } catch (e: IOException) {
                            Log.e("Flickr Cats Error", "Error closing stream", e)
                        }
                    }
                }
            }
            thread.start()
        }

        val btnOkHTTP = findViewById<Button>(R.id.btnOkHTTP)
        btnOkHTTP.setOnClickListener {
            val thread = Thread {
                val url2 = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url2)
                    .build()

                try {
                    val response: Response = client.newCall(request).execute()
                    Log.i("Flickr OkCats", response.body()!!.string())
                } catch (e: IOException) {
                    Log.e("Flickr OkCats Error", "Error ", e)
                }
            }
            thread.start()
        }
    }
}
