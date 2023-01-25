package com.joel.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.domain.model.comics.comicdetails.ComicDetails
import com.joel.comics.domain.model.comics.comicdetails.ComicDetailsResult
import com.joel.comics.domain.model.comics.comicharacters.ComicCharacters
import com.joel.comics.domain.network.comics.ComicsRepository
import com.joel.comics.domain.paginate.comics.AllComicsSource
import com.joel.comics.domain.paginate.comics.COMICS_PAGE_SIZE
import com.joel.comics.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: ComicsRepository
) : ViewModel() {

    val allComics : Flow<PagingData<AllComicsResult>> = Pager(PagingConfig(pageSize = COMICS_PAGE_SIZE)){
        AllComicsSource(repository)
    }.flow.cachedIn(viewModelScope)

    suspend fun getComicDetails(comicId : Int) : Resource<ComicDetailsResult>{
        val details = repository.getComicDetails(comicId)
        Timber.d(details.data.toString())
        return details
    }

    suspend fun getComicCharacters(comicId : Int) : Resource<ComicCharacters>{
        val characters = repository.getComicCharacters(comicId)
        Timber.d(characters.data.toString())
        return characters
    }

}