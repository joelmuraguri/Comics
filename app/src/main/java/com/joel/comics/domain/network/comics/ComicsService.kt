package com.joel.comics.domain.network.comics

import com.joel.comics.domain.model.comics.allcomics.AllComics
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {

    @GET("comics")
    suspend fun getComics(
        @Query("apikey") apikey : String = Constants.MARVEL_API_KEY,
        @Query("ts") ts : String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : AllComics


    @GET("comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId : Int,
        @Query("apikey") apikey : String = Constants.MARVEL_API_KEY,
        @Query("ts") ts : String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
    ) : AllComicsResult

    suspend fun getCharacterByComic()

    suspend fun getSeriesByComic()
}