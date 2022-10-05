package com.joel.comics.viewmodel

import androidx.lifecycle.ViewModel
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.ResultX
import com.joel.comics.model.network.MarvelRepositoryImpl
import com.joel.comics.model.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val repository  : MarvelRepositoryImpl
    ) : ViewModel() {

    suspend fun getMarvelHero(characterId : Int) : Resource<ResultX>{
        return repository.getMarvelCharacter(characterId)
    }

}