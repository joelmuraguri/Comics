package com.joel.comics.domain.network.characters

import com.joel.comics.domain.model.characters.allcharacters.AllCharacters
import com.joel.comics.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {



    @GET("characters")
   suspend fun getCharacters(
        @Query("apikey") apikey : String = Constants.MARVEL_API_KEY,
        @Query("ts") ts : String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
   ) : AllCharacters

   suspend fun getCharacterById()

   suspend fun getComicByCharacter()

   suspend fun getSeriesByCharacter()
}