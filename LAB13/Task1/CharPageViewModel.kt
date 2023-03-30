package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.AppWithCompanion.Companion.charApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class CharPageViewModel : ViewModel() {
    private val charData: MutableLiveData<List<RecyclerItem>> by lazy {
        MutableLiveData<List<RecyclerItem>>().also {
            runBlocking { launch { loadCharacterData(1) }
            }
        }
    }

    fun getCharData(): LiveData<List<RecyclerItem>> {
        return charData
    }

    private fun loadCharacterData(pageId: Int) {
        GlobalScope.launch {
            charApi.getCharactersList(pageId)
                .enqueue(object : Callback<RecyclerItem.CharactersData> {
                    override fun onResponse(call: Call<RecyclerItem.CharactersData>,
                                            response: Response<RecyclerItem.CharactersData>) {
                        if (response.isSuccessful) {
                            updateList(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<RecyclerItem.CharactersData>, t: Throwable) {
                    }
                })
        }
    }

    fun uploadPage(url: String) {
        val id = url.substring(url.lastIndexOf("=")+ 1).toInt()
        runBlocking { launch { loadCharacterData(id) } }
    }

    private fun updateList(pageData: RecyclerItem.CharactersData) {
        if (charData.value.isNullOrEmpty()) {
            charData.value = pageData.results + pageData.info
        }
        else {
            val addiction = if (pageData.info.next.isEmpty())
                pageData.results else pageData.results + pageData.info
            charData.value = charData.value!!.dropLast(1) + addiction
        }
    }
}
