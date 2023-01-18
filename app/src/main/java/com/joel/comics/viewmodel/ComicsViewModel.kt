package com.joel.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.domain.network.comics.ComicsRepository
import com.joel.comics.domain.paginate.comics.AllComicsSource
import com.joel.comics.domain.paginate.comics.COMICS_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: ComicsRepository
) : ViewModel() {

    val allComics : Flow<PagingData<AllComicsResult>> = Pager(PagingConfig(pageSize = COMICS_PAGE_SIZE)){
        AllComicsSource(repository)
    }.flow.cachedIn(viewModelScope)

}