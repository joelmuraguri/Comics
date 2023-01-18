package com.joel.comics.domain.network.comics

import com.joel.comics.domain.model.comics.allcomics.AllComics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsRepository @Inject constructor(
    private val api : ComicsService
)  {

    suspend fun getComics(offset : Int, limit :Int) : AllComics{
        return api.getComics(limit = limit, offset = offset)
    }


}