package com.joel.comics.domain.network.characters

import com.joel.comics.domain.model.characters.allcharacters.AllCharacters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(
    private val api : CharacterService
){
    suspend fun getCharacters(offset : Int, limit :Int) : AllCharacters {
        return api.getCharacters(limit = limit, offset = offset)
    }
}