package com.joel.comics.model.network

import android.util.Log
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Character
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService
) {

    suspend fun getMarvelHeroes(
        offset: Int,
        limit : Int
    ): Resource<Character>{
      val response = try {
          apiService.getMarvelHeroes(
              offset = offset,
              limit = limit
          )
      }
      catch (e : HttpException){
          Log.d("TESTER::", e.code().toString());
          if (e.code() == 409){
              return Resource.Error(message = "Missing Parameters : Check your Timestamp, Api key and hash")
          }
          else if (e.code() == 401){
             return Resource.Error(message = "Invalid Referrers and Hash : Check your ts, Apikey & Hash")
          }
          else if (e.code() == 405){
              return Resource.Error(message = "Method Not Allowed")
          }
          else if (e.code() == 403){
              return Resource.Error(message = "Forbidden")
          }
          else{
              return Resource.Error(message = "Check your Internet Connection")
          }
      }
        return Resource.Success(response)
    }
}