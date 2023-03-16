package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharPageViewModel : ViewModel() {
    private val charData: MutableLiveData<List<Character>> by lazy {
        MutableLiveData<List<Character>>().also {
            loadCharacterData()
        }
    }

    fun getCharData(): LiveData<List<Character>> {
        return charData
    }

    private fun loadCharacterData() {
        val retrofitService = Common.retrofitCharService
        retrofitService.getCharactersList().enqueue(object : Callback<CharactersData> {
            override fun onResponse(call: Call<CharactersData>,
                                    response: Response<CharactersData>) {
                charData.value = response.body()?.results
            }

            override fun onFailure(call: Call<CharactersData>, t: Throwable) {
            }
        })
    }
}
