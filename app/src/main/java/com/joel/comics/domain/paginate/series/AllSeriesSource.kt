package com.joel.comics.domain.paginate.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.comics.domain.model.series.allseries.AllSeriesResult
import com.joel.comics.domain.network.series.SeriesRepository

const val SERIES_PAGE_SIZE = 30
private const val INITIAL_LOAD_SIZE = 0

class AllSeriesSource(
    private val repository: SeriesRepository
) : PagingSource<Int, AllSeriesResult>() {

    override fun getRefreshKey(state: PagingState<Int, AllSeriesResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllSeriesResult> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position-1) * SERIES_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

        return  try {
            val response = repository.getSeries(offset = offset, limit = params.loadSize).data.results
            val nextKey = if (response.isEmpty()){
                null
            } else{
                position + (params.loadSize  / SERIES_PAGE_SIZE)
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