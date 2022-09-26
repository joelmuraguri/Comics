package com.joel.comics.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.joel.comics.model.marvelmodel.MarvelListEntry
import com.joel.comics.model.network.MarvelRepo
import com.joel.comics.model.paginate.MarvelSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelHomeViewModel @Inject constructor(
    private val repository: MarvelRepo
) : ViewModel()
{
    val characters = Pager(PagingConfig(pageSize = 20)){
        Log.d("LIST","characters")
        MarvelSource(repository)
    }.flow.cachedIn(viewModelScope)

}