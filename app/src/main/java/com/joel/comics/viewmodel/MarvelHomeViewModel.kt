package com.joel.comics.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.comics.model.marvelmodel.MarvelListEntry
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Result
import com.joel.comics.model.network.MarvelRepository
import com.joel.comics.model.network.Resource
import com.joel.comics.utils.Constants.Companion.PAGE_SIZE
import com.joel.comics.utils.getImageLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelHomeViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel()
{

    private var currentPage = 0

    var marvelList = mutableStateOf<List<MarvelListEntry>>(listOf())
    var loadedError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    var loadedMarvelList = listOf<MarvelListEntry>()
    var isStartSearching = false
    var isSearching = mutableStateOf(false)

     init {
         loadMarvelCharacters()
     }

    fun searchCharacter(query : String){
        val listToSearch = if (isSearching.value){
            marvelList.value
        }
        else{
            loadedMarvelList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()){
                marvelList.value = loadedMarvelList
                isSearching.value = false
                isStartSearching = false
                return@launch
            }

            val results = listToSearch.filter {
                it.characterName.contains(query.trim(), ignoreCase = true) || it.number.toString()  == query.trim()
            }

            if (isStartSearching){
                loadedMarvelList = marvelList.value
                isStartSearching = false
            }
            marvelList.value = results
            isSearching.value = true
        }
    }


    fun loadMarvelCharacters(){
        Log.d("ViewModel", "Load Characters")
        viewModelScope.launch {
            isLoading.value = true

            val result = repository.getMarvelHeroes(
                limit = PAGE_SIZE,
                offset = currentPage * PAGE_SIZE,
            )

            when(result){
                is Resource.Success -> {
                    endReached.value = (PAGE_SIZE * currentPage) >= result.data!!.data.total
                   val marvelEntries = result.data.data.results.mapIndexed { index, marvel ->

                       MarvelListEntry(
                           characterName = marvel.name,
                           imageUrl = getImageLink(marvel.thumbnail),
                           number = marvel.id
                       )
                   }

                    currentPage++
                    isLoading.value = false
                    loadedError.value = ""
                    marvelList.value += marvelEntries
                }
                is Resource.Error -> {
                    Log.d("viewmodel", "the error is ${result.message}")
                    loadedError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}