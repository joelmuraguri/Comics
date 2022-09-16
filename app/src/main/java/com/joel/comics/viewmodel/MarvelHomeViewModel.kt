package com.joel.comics.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import com.joel.comics.model.network.MarvelRepository
import com.joel.comics.model.network.Resource
import com.joel.comics.utils.Constants.Companion.PAGE_SIZE
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelHomeViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel()
{

    private var currentPage = 0

    var marvelList = mutableStateOf<List<Character>>(listOf())
    var loadedError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)




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
                    val marvelEntries = result.data!!.data.results.mapIndexed { index, marvel ->




                    }
                }
                is Resource.Error -> {
                    loadedError.value = result.message!!
                    isLoading.value = false

                }
                else -> {


                }
            }
        }
    }
}