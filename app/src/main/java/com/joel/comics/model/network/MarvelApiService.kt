package com.joel.comics.model.network

import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.ResultX
import com.joel.comics.utils.Constants
import com.joel.comics.utils.Constants.Companion.CHARACTER_ENDPOINT
import com.joel.comics.utils.Constants.Companion.CHARACTER_ID_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiService {

    @GET(CHARACTER_ENDPOINT)
    suspend fun getMarvelHeroes(
        @Query("apikey") apikey : String = Constants.MARVEL_API_KEY,
        @Query("ts") ts : String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : Character


    @GET(CHARACTER_ID_ENDPOINT)
    suspend fun getMarvelCharacter(
        @Path("characterId") id : Int
    ) : ResultX

}