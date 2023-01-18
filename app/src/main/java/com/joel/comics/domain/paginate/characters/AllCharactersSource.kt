package com.joel.comics.domain.paginate.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.comics.domain.model.characters.allcharacters.AllCharactersResult
import com.joel.comics.domain.network.characters.CharactersRepository

const val CHARACTER_PAGE_SIZE = 30
private const val INITIAL_LOAD_SIZE = 0

class AllCharactersSource(
    private val repository: CharactersRepository
) : PagingSource<Int, AllCharactersResult>() {

    override fun getRefreshKey(state: PagingState<Int, AllCharactersResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllCharactersResult> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position-1) * CHARACTER_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

        return  try {
            val response = repository.getCharacters(offset = offset, limit = params.loadSize).data.results
            val nextKey = if (response.isEmpty()){
                null
            } else{
                position + (params.loadSize  / CHARACTER_PAGE_SIZE)
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