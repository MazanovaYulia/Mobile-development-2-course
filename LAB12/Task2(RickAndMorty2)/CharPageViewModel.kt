package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CharPageViewModel : ViewModel() {
    private val charData: MutableLiveData<List<RecyclerItem>> by lazy {
        MutableLiveData<List<RecyclerItem>>().also {
            loadCharacterData()
        }
    }

    fun getCharData(): LiveData<List<RecyclerItem>> {
        return charData
    }

    private fun loadCharacterData() {
        val retrofitService = Common.retrofitCharService
        retrofitService.getCharactersList().enqueue(object : Callback<RecyclerItem.CharactersData> {
            override fun onResponse(call: Call<RecyclerItem.CharactersData>,
                                    response: Response<RecyclerItem.CharactersData>) {
                val charList = response.body()!!.results
                val button = response.body()!!.info
                charData.value = charList + button
            }

            override fun onFailure(call: Call<RecyclerItem.CharactersData>, t: Throwable) {
            }
        })
    }

    fun uploadPage(url: String) {
        val executorService: ExecutorService = Executors.newFixedThreadPool(2)
        val pageData = executorService.submit(Callable {
            val connection = URL(url).openConnection() as HttpURLConnection
            val jsonData = connection.inputStream.bufferedReader().readText()
            connection.disconnect()
            Gson().fromJson(jsonData, RecyclerItem.CharactersData::class.java)
        })
        updateList(pageData.get())
    }

    private fun updateList(pageData: RecyclerItem.CharactersData) {
        val newList = charData.value!!.dropLast(1).toMutableList()
        val newCharList = pageData.results
        val button = pageData.info
        charData.value = if (button.next.isNotEmpty())
            newList + newCharList + button else newList + newCharList
    }
}
