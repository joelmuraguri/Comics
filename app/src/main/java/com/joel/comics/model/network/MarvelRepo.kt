package com.joel.comics.model.network

import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.ResultX

interface MarvelRepo {

    suspend fun getMarvelHeroes(
        limit : Int,
        offset : Int
    ) : Character

    suspend fun getMarvelCharacter( characterId : Int) : Resource<ResultX>
}