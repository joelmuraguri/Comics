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
import dagger.hilt.android.lifecycle.HiltViewModel
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

     init {
         loadMarvelCharacters()
     }


    fun loadMarvelCharacters(){
        viewModelScope.launch {
            isLoading.value = true

            val result = repository.getMarvelHeroes(
                limit = PAGE_SIZE,
                offset = (currentPage * PAGE_SIZE),
            )

            when(result){
                is Resource.Success -> {

                    endReached.value = (PAGE_SIZE * currentPage) >= result.data!!.data.total
                   val marvelEntries = result.data.data.results.mapIndexed { index, marvel ->
                       val url = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                       val startIndex = 44
                       val endIndex = 57
                       println(url[startIndex])
                       println(url[endIndex])
                       val value = url.subSequence(startIndex,endIndex)
                       val imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/${value}.jpg"

                       MarvelListEntry(
                           characterName = marvel.name,
                           imageUrl = imageUrl,
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