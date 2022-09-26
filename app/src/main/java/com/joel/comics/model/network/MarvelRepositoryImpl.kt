package com.joel.comics.model.network

import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

@ActivityScoped
class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService
) : MarvelRepo {

    override suspend fun getMarvelHeroes(limit: Int, offset: Int): Character {
        delay(3000L)
        return apiService.getMarvelHeroes(limit = limit, offset = offset)
    }


}