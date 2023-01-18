package com.joel.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joel.comics.domain.model.series.allseries.AllSeriesResult
import com.joel.comics.domain.network.series.SeriesRepository
import com.joel.comics.domain.paginate.comics.COMICS_PAGE_SIZE
import com.joel.comics.domain.paginate.series.AllSeriesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repository: SeriesRepository
) : ViewModel() {


    val allSeries : Flow<PagingData<AllSeriesResult>> = Pager(PagingConfig(pageSize = COMICS_PAGE_SIZE)){
        AllSeriesSource(repository)
    }.flow.cachedIn(viewModelScope)

}