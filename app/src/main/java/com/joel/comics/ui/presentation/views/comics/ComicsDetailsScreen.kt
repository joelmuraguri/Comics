package com.joel.comics.ui.presentation.views.comics

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.ui.presentation.components.DetailsContent
import com.joel.comics.utils.Resource
import com.joel.comics.viewmodel.ComicsViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ComicsDetailsScreen(
    viewModel : ComicsViewModel = hiltViewModel()
){

    val scrollState = rememberLazyListState()

    val id = 82967

    val details = produceState<Resource<AllComicsResult>>(initialValue = Resource.Loading()) {
        value = viewModel.getComicDetails(id)
    }.value


    if (details is Resource.Success){
        DetailsContent()
    } else{
        CircularProgressIndicator()
    }

}