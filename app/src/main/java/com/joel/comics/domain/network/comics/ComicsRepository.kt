package com.joel.comics.domain.network.comics

import com.joel.comics.domain.model.comics.allcomics.AllComics
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.domain.model.comics.comicdetails.ComicDetails
import com.joel.comics.domain.model.comics.comicdetails.ComicDetailsResult
import com.joel.comics.domain.model.comics.comicharacters.ComicCharacters
import com.joel.comics.utils.Resource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsRepository @Inject constructor(
    private val api : ComicsService
)  {

    suspend fun getComics(offset : Int, limit :Int) : AllComics{
        return api.getComics(limit = limit, offset = offset)
    }

    suspend fun getComicCharacters(comicId: Int)  : Resource<ComicCharacters>{
        val response = try {
            api.getCharacterByComic(comicId)
        } catch (e : Exception){
            Timber.d(e.message)
            return  Resource.Error("Unknown Error Occurred")
        }

        Timber.d("Comics Details $response")
        return Resource.Success(response)

    }

    suspend fun getComicDetails(comicId : Int) : Resource<AllComicsResult>{
        val response = try {
            api.getComicById(comicId)
        }
        catch (e : Exception){
            Timber.d(e.message)
            return Resource.Error(message = "Unknown Error Occurred")
        }

        Timber.d("Comics Details $response")
        return Resource.Success(response)
    }


}