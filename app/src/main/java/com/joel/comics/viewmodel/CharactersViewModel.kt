package com.joel.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joel.comics.domain.model.characters.allcharacters.AllCharactersResult
import com.joel.comics.domain.network.characters.CharactersRepository
import com.joel.comics.domain.paginate.characters.AllCharactersSource
import com.joel.comics.domain.paginate.comics.COMICS_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    val allCharacters : Flow<PagingData<AllCharactersResult>> = Pager(PagingConfig(pageSize = COMICS_PAGE_SIZE)){
        AllCharactersSource(repository)
    }.flow.cachedIn(viewModelScope)
}