package com.joel.comics.model.network

import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.ResultX
import kotlinx.coroutines.delay
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService
) : MarvelRepo {

    override suspend fun getMarvelHeroes(limit: Int, offset: Int): Character {
        delay(3000L)
        return apiService.getMarvelHeroes( offset = offset, limit = limit)
    }

    override suspend fun getMarvelCharacter(characterId: Int): Resource<ResultX> {
        val response = try {
            apiService.getMarvelCharacter(characterId)
        }
        catch (e:HttpException){
            if (e.code() == 409){
               return Resource.Error(message = "Error 409")
            }
            else if (e.code() == 401){
                return Resource.Error(message = "Error 401")

            }
            else if (e.code() == 405){
                return Resource.Error(message = "Error 405")

            }
            else if (e.code() == 403){
                return Resource.Error(message = "Error 403")
            }
            else{
                return Resource.Error(message = "Unknown Error")
            }
        }
        return Resource.Success(response)
    }
}