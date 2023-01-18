package com.joel.comics.domain.paginate.comics

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.domain.network.comics.ComicsRepository

const val COMICS_PAGE_SIZE = 30
private const val INITIAL_LOAD_SIZE = 0

class AllComicsSource(
    private val repository: ComicsRepository
) : PagingSource<Int, AllComicsResult>() {

    override fun getRefreshKey(state: PagingState<Int, AllComicsResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllComicsResult> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position-1) * COMICS_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

        return  try {
            val response = repository.getComics(offset = offset, limit = params.loadSize).data.results
            val nextKey = if (response.isEmpty()){
                null
            } else{
                position + (params.loadSize  / COMICS_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        }
        catch ( e: Exception){
            LoadResult.Error(e)
        }
    }
}