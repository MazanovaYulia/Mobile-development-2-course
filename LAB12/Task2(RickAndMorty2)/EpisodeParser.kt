package com.example.rickandmorty

import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val baseUrl = "https://rickandmortyapi.com/api/episode/"

class EpisodeParser(characterData: RecyclerItem.CharacterItem) {
    private val charEpIdList = getEpisodeIdList(characterData.episode)
    private val reqUrl = buildRequestUrl()
    private val episodeList = loadEpisodeData()
    val listOfEpisodes: List<Episode>
        get() = episodeList

    private fun loadEpisodeData(): List<Episode> {
        val executorService: ExecutorService = Executors.newFixedThreadPool(2)
        val episodeList = executorService.submit(Callable {
            val connection = URL(reqUrl).openConnection() as HttpURLConnection
            val jsonData = connection.inputStream.bufferedReader().readText()
            connection.disconnect()
            val episodeData = Gson().fromJson(jsonData, Array<Episode>::class.java).toList()
            filterEpisodeData(episodeData)
        })
        return episodeList.get()
    }

    private fun filterEpisodeData(episodeData: List<Episode>): MutableList<Episode> {
        val filteredEpisodeData = mutableListOf<Episode>()
        episodeData.forEach { episode ->
            if (episode.id in charEpIdList) {
                filteredEpisodeData.add(episode)
            }
        }
        return filteredEpisodeData
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
        var requestUrl = baseUrl
        charEpIdList.forEach { id ->
            requestUrl += "$id,"
        }
        return requestUrl
    }
}
