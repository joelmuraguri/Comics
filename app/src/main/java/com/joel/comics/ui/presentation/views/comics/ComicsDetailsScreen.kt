package com.joel.comics.ui.presentation.views.comics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.joel.comics.domain.model.comics.comicdetails.ComicDetailsResult
import com.joel.comics.domain.model.comics.comicharacters.ComicCharacters
import com.joel.comics.ui.presentation.components.ComicImageBanner
import com.joel.comics.utils.Resource
import com.joel.comics.utils.getComicDetailsImageLink
import com.joel.comics.viewmodel.ComicsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ComicsDetailsScreen(
    viewModel : ComicsViewModel = hiltViewModel(),
    comicId : Int,
    navigator: DestinationsNavigator
){

    val scrollState = rememberLazyListState()

    val details = produceState<Resource<ComicDetailsResult>>(initialValue = Resource.Loading()) {
        value = viewModel.getComicDetails(comicId)
    }.value

    val characters = produceState<Resource<ComicCharacters>>(initialValue = Resource.Loading()) {
        value = viewModel.getComicCharacters(comicId)
    }.value



    Box {
        if (details is Resource.Success){
            details.data?.thumbnail?.let { getComicDetailsImageLink(it) }?.let {
                ComicImageBanner(
                    navigator = navigator,
                    scrollState = scrollState,
                    comicName = details.data.title.toString(),
                    comicImageURL = it,
                )
            }
        } else{
                CircularProgressIndicator()
        }
    }
}