package com.example.mydialer2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val adapter: Adapter = Adapter { number ->
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(callIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {

                    val recyclerView = findViewById<RecyclerView>(R.id.rView)
                    val searchEditText = findViewById<EditText>(R.id.et_search)

                    val json = response?.body()?.string()
                    val gson = GsonBuilder().create()
                    var phones = gson.fromJson(json, Array<Contact>::class.java)
                        .toList() as ArrayList<Contact>

                    this@MainActivity.runOnUiThread {
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = adapter

                        adapter.submitList(phones)
                        searchEditText.addTextChangedListener {
                            val filteredPhones = phones.filter { item ->
                                item.phone.contains(it.toString()) ||
                                        item.name.contains(it.toString()) ||
                                        item.type.contains(it.toString())
                            }
                            adapter.submitList(filteredPhones)

                            val filterState = getSharedPreferences("app_preferences", MODE_PRIVATE).edit()
                            filterState.putString("SEARCH_FILTER", it.toString()).apply()
                        }

                        val lastFilterText = getSharedPreferences("app_preferences", MODE_PRIVATE).getString("SEARCH_FILTER", "")
                        searchEditText.append(lastFilterText)
                    }
                }
            }
        })
    }
}
