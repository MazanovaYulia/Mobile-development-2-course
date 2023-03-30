package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val NUMBER_PAGE_INDEX = 1

class CharactersViewModel : ViewModel() {
    private val mService = App.apiService
    val characterData: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val characterList: LiveData<List<CharacterItem>> get() = characterData
    private lateinit var savedList: List<CharacterItem>
    private var copyOfCharacters = mutableListOf<CharacterItem.CharacterInfo>()

    init {
        getCharacters(1)
    }

    private fun getCharacters(page: Int) {
        characterData.value = characterData.value.let { it?.slice(0 until (it.size - 1)) }
        GlobalScope.launch {
            mService.getCharactersList(page).enqueue(object : Callback<CharacterData> {
                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {
                    characterData.value = convertResponseToCharList(response)
                    savedList = characterData.value!!
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                    characterData.value = emptyList()
                }
            })
        }
    }

    private fun convertResponseToCharList(resp: Response<CharacterData>): List<CharacterItem> {
        val oldData = characterData.value ?: listOf()
        val tempStorage = mutableListOf<CharacterItem>()
        resp.body()?.let { responceBody ->
            responceBody.results.forEach { char ->
                tempStorage.add(CharacterItem.CharacterInfo(char))
                copyOfCharacters.add(CharacterItem.CharacterInfo(char))
            }
            responceBody.info.next?.let { linkToNextPage ->
                val nexPageNumber = linkToNextPage.split('=')[NUMBER_PAGE_INDEX].toInt()
                tempStorage.add(CharacterItem.NextPage(nexPageNumber))
            }
        }
        return oldData.plus(tempStorage)
    }

    fun handleClick(position: Int): Boolean {
        return if (characterData.value?.get(position) is CharacterItem.NextPage) {
            (characterData.value?.get(position) as CharacterItem.NextPage).nextpageid?.let {
                getCharacters(it)
            }
            false
        } else {
            true
        }
    }

    fun getCharacterByPosition(pos: Int): CharacterItem? {
        return characterData.value?.get(pos)
    }

    fun search(s: CharSequence) {
         val results = mutableListOf<CharacterItem>()
         if (s.isNotBlank()) {
             copyOfCharacters.forEach { character ->
                 if (character.character.name.lowercase(Locale.getDefault()).contains(s)) {
                     results.add(character)
                 }
             }
         } else {
             results.addAll(savedList)
         }
         characterData.value = results
    }
}
