package com.joel.comics.model.network

import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton


interface MarvelRepo {

    suspend fun getMarvelHeroes(
        limit : Int,
        offset : Int
    ) : Character
}