package com.example.rickandmorty

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class EpisodeListParser (characterData: RecyclerItem.CharacterItem) {
    private val charEpIdList = getEpisodeIdList(characterData.episode)
    private val reqUrl = buildRequestUrl()
    private val episodeList: MutableLiveData<List<Episode>> by lazy {
        MutableLiveData<List<Episode>>().also {
            runBlocking { launch { loadEpisodeData() }
            }
        }
    }

    fun getEpisodes(): MutableLiveData<List<Episode>> {
        return episodeList
    }

    private suspend fun loadEpisodeData() = coroutineScope {
        AppWithCompanion.epApi.getEpisodesList(reqUrl).enqueue(object : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                episodeList.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
            }
        })
    }

    private fun getEpisodeIdList(urls: List<String>): MutableList<Int> {
        val episodeIdList = mutableListOf<Int>()
        urls.forEach { url ->
            val id = url.substring(url.lastIndexOf("/") + 1).toInt()
            episodeIdList.add(id)
        }
        return episodeIdList
    }

    private fun buildRequestUrl(): String {
        var requestUrl = ""
        charEpIdList.forEach { id ->
            requestUrl += "$id,"
        }
        return requestUrl
    }
}
