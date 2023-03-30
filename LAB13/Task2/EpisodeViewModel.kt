package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpisodeViewModel : ViewModel() {
    private val mService = App.apiService
    val episodesData: MutableLiveData<List<EpItem>> = MutableLiveData()
    val episodeList: LiveData<List<EpItem>> get() = episodesData

    fun getInfo(episodes: ArrayList<String>) {
        val numbersOfEpisode: ArrayList<Int> = arrayListOf()
        for (index in episodes.indices) {
            numbersOfEpisode.add(episodes[index].substring(40).toInt())
        }
        GlobalScope.launch {
            mService.getEpisodeList(numbersOfEpisode).enqueue(object : Callback<EpisodeData> {
                override fun onResponse(
                    call: Call<EpisodeData>,
                    response: Response<EpisodeData>
                ) {
                    episodesData.value = response.body() as ArrayList<EpItem>
                }

                override fun onFailure(call: Call<EpisodeData>, t: Throwable) {
                    episodesData.value = emptyList()
                }
            })
        }
    }

}
