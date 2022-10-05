package com.joel.comics.model.paginate

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Result
import com.joel.comics.model.network.MarvelRepo
import com.joel.comics.model.network.MarvelRepositoryImpl
import retrofit2.HttpException


class MarvelSource (
    private val repository: MarvelRepo
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPosition = state.closestPageToPosition(position)
            anchorPosition?.prevKey?.plus(1) ?: anchorPosition?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        Log.d("LIST::","load characters")
      return try {
          val nextPage = params.key ?: 0
          val response = repository.getMarvelHeroes(limit = 100, offset = nextPage)
          LoadResult.Page(
              data =  response.data.results,
              prevKey =  if (nextPage == 1) null else nextPage - 1,
              nextKey = if(response.data.results.isNotEmpty()) response.data.offset + 1 else null
          )
      }
      catch (e:HttpException){
          if (e.code() == 409){
            LoadResult.Error(e)
        }
        else if (e.code() == 401){
              LoadResult.Error(e)
        }
        else if (e.code() == 405){
              LoadResult.Error(e)
        }
        else if (e.code() == 403){
              LoadResult.Error(e)
        }
        else{
              LoadResult.Error(e)
        }
      }
      catch (e: Exception){
            LoadResult.Error(e)
      }

    }
}